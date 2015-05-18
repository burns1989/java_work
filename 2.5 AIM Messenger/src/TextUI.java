import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TextUI is the driver class for the SAIM front-end.
 **/
public class TextUI {

	public static void main(String[] args) {

		Logger logger = Logger.getLogger("edu.mit.tbp.se.chat");

		// parse arguments

		CommandArgs parsedArgs;
		try {
			parsedArgs = CommandArgs.parseArgs(args);
		} catch (IllegalArgumentException e) {
			System.err.println(e);
			System.err.println(CommandArgs.getUsage());
			System.exit(-1);
			return;
		}

		// use argument information

		if (parsedArgs.debug) {
			ConsoleHandler ch = new ConsoleHandler();
			logger.setLevel(parsedArgs.debugLevel);
			logger.addHandler(ch);
			ch.setLevel(parsedArgs.debugLevel);
		} else {
			logger.setLevel(Level.OFF);
		}

		String username = parsedArgs.username;
		String password = parsedArgs.password;

		FLAPConnection fc = new FLAPConnection();
		MessageLayer ml = new MessageLayer(fc);
		TOCMessage message;

		// start the login procedure

		try {
			fc.connect(username);
			ml.login(username, password);
		} catch (Exception e) {
			logger.severe("SignOn failed: " + e);
			e.printStackTrace();
			System.exit(-1);
			return;
		}

		// now spawn the server listening thread
		MessageReceiver mr = new MessageReceiver(ml, System.out);
		Thread mrThread = new Thread(mr, "AIM Server Listening Thread");
		// make it daemonic to so that it will be killed when the user exits.
		mrThread.setDaemon(true);
		// finally, start it
		mrThread.start();

		// and finally, get this guy stuck in a read loop
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		CommandReader cr = new CommandReader(br, System.out, ml);
		cr.run();

		// when cr.run() returns this should exit (hopefully cleaning up
		// the server listening thread).
	}
}

/**
 * A MessageReceiver continually tries to read messages from the server.
 **/
class MessageReceiver implements Runnable {

	final static Logger logger = Logger.getLogger("edu.mit.tbp.se.chat");

	/** the high-level connection on which this listens **/
	private MessageLayer ml;

	/** the destination for messages **/
	private PrintStream out;

	/**
	 * Create a new MessageReceiver.
	 * 
	 * @param ml
	 *            the connection to listen to
	 * @param out
	 *            where this should print incoming messages
	 **/
	public MessageReceiver(MessageLayer ml, PrintStream out) {
		this.ml = ml;
		this.out = out;
	}

	/**
	 * Start listening. This will listen forever.
	 **/
	public void run() {
		while (true) {
			TOCMessage message;
			try {
				message = ml.receiveMessage();
			} catch (IOException e) {
				logger.severe(e.toString());
				continue;
			} catch (AIMErrorException e) {
				this.out.println("\nServer sent error code " + e.getError());
				logger.warning(e.toString());
				continue;
			}

			if (message instanceof ServerIMIn2Message) {
				ServerIMIn2Message im = (ServerIMIn2Message) message;
				this.out.println("\n" + im.getSourceUser() + ": " + im.getMessage());
			}
		}
	}
}