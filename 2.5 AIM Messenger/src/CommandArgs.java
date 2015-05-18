
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A CommandArgs structure parses an array of String arguments for a username, a password, and optional logging
 * information.
 * 
 **/
class CommandArgs {

	/** the username **/
	public String username;
	/** the password **/
	public String password;
	/** debugging flag **/
	public boolean debug;
	/** if debug is true, what level to use **/
	public Level debugLevel;

	/**
	 * Create a new CommandArgs. Use the parseArgs factory method instead.
	 * 
	 * @see #parseArgs
	 **/
	private CommandArgs() {
		this.debug = false;
		// everything else is set to null by the compiler.
	}

	/**
	 * Parse the command line arguments into a CommandArgs structure.
	 * 
	 * @param args
	 *            the command line to parse
	 * @throws IllegalArgumentException
	 *             if the arguments cannot be parsed.
	 * @return a new CommandArgs structure representing the parse arguments.
	 **/
	public static CommandArgs parseArgs(String[] args) {

		CommandArgs rval = new CommandArgs();

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];

			if (arg.startsWith("--")) {
				// this is an option
				if (2 == arg.indexOf("log")) {
					// --log option
					rval.debug = true;

					// now determine log level
					String[] split = arg.split("=", 2);
					if (2 == split.length) {
						rval.debugLevel = Level.parse(split[1].toUpperCase());
					} else {
						// set default level to SEVERE
						rval.debugLevel = Level.SEVERE;
					}
				} else {
					throw new IllegalArgumentException("unknown option: " + arg);
				}
			} else if (null == rval.username) {
				rval.username = arg;
			} else if (null == rval.password) {
				rval.password = arg;
			} else {
				throw new IllegalArgumentException("too many arguments");
			}
		}

		// now that we've checked for the existence of options, lets check
		// for the absence of required arguments.
		if ((null == rval.username) || (null == rval.password)) {
			throw new IllegalArgumentException("too few arguments");
		}
		return rval;
	}

	/**
	 * Get the command line usage that this expects.
	 * 
	 * @return a String with command-line usage.
	 **/
	public static String getUsage() {
		StringBuffer sb = new StringBuffer();

		sb.append("Usage: java " + TextUI.class.getName() + " [--log=LEVEL] username password\n");
		sb.append("where:\n");
		sb.append("\tLEVEL is one of SEVERE, WARNING, INFO, CONFIG, FINE, FINER, or FINEST.\n");
		sb.append("\tusername is the username to use\n");
		sb.append("\tpassword is the password for the username\n");

		return sb.toString();
	}
}



/**
 * A CommandReader continually tries to read and act on commands from the user.
 **/
class CommandReader implements Runnable {

	final static Logger logger = Logger.getLogger("edu.mit.tbp.se.chat");

	/** the prompt displayed before the user types something **/
	private String prompt = ">>> ";

	/** the output stream to use **/
	private PrintStream out;
	/** object from which to get input **/
	private BufferedReader in;
	/** connection to use to send messages **/
	private MessageLayer ml;
	/** state variable **/
	private boolean running;

	/**
	 * Create a new CommandReader.
	 * 
	 * @param in
	 *            provider of user input
	 * @param out
	 *            output sink
	 * @param ml
	 *            connection to use
	 **/
	public CommandReader(BufferedReader in, PrintStream out, MessageLayer ml) {
		this.in = in;
		this.out = out;
		this.ml = ml;
		this.running = true;
	}

	/**
	 * Start the command interpreter. This will return when the user uses the "exit" command.
	 **/
	public void run() {
		while (this.running) {
			commandIteration();
		}
	}

	/**
	 * Single iteration of the interpreter
	 **/
	private void commandIteration() {
		this.out.print(this.prompt);
		this.out.flush();

		try {
			String input = this.in.readLine();
			parse(input);
		} catch (IOException e) {
			logger.severe("Could not read input: " + e);
		}
	}

	/**
	 * Parse and execute a command.
	 * 
	 * @param command
	 *            the command to parse.
	 **/
	private void parse(String command) {
		String[] cmdFields = command.split(" ", 2);
		if ("exit".equals(cmdFields[0])) {
			// quit
			this.running = false;
		} else if ("help".equals(cmdFields[0])) {

			// print a usage statement
			StringBuffer sb = new StringBuffer();
			sb.append("The following commands are available:\n");
			sb.append("\thelp -- this help message\n");
			sb.append("\texit -- logout and exit the AIM client\n");
			sb.append("\tsendim screenname message... -- send a message to screenname.").append(
					"  You do not need to put your message in quotes.");

			this.out.println(sb.toString());

		} else if ("sendim".equals(cmdFields[0])) {

			// send an IM
			String errorMessage = "sendim invalid.  use \"sendim buddyname message\"";
			if (2 != cmdFields.length) {
				this.out.println(errorMessage);
				return;
			}

			String[] sendIMFields = cmdFields[1].split(" ", 2);
			if (2 != sendIMFields.length) {
				this.out.println(errorMessage);
				return;
			}

			TOCMessage message = new Toc2SendIMMessage(sendIMFields[0], sendIMFields[1]);
			try {
				this.ml.sendMessage(message);
			} catch (IOException e) {
				logger.warning("Could not send message: " + e);
			}

		}
	}
}