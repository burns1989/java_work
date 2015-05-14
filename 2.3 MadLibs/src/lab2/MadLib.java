package lab2;

import java.util.ArrayList;


public class MadLib extends MadLibTemplate {
	
	protected ArrayList<MadLibEntry> madLibs = new ArrayList<MadLibEntry>();
	
	public MadLib(String title) {
		super(title);
	}

	@Override
	void addString(String text) {
		madLibs.add(new TextEntry(text));
	}

	@Override
	void addSlot(String type) {
		madLibs.add(new Slot(type));
	}

	@Override
	void printAsTemplate(UserInterface ui) {
		// Go through all the mad lib entries and print out the template
		for (MadLibEntry madLib : madLibs) {
			ui.writeString(madLib.templateString());
		}
	}

	@Override
	void doLib(UserInterface ui) {
		// Go through all the mad lib entries and prompt the user for input for the slots
		for (MadLibEntry madLib : madLibs) {
			madLib.doLib(ui);
		}
	}

	@Override
	void printAsMadLib(UserInterface ui) {
		// Go through all the mad lib entries and print out the mad lib
		for (MadLibEntry madLib : madLibs) {
			ui.writeString(madLib.madLibString());
		}
	}

}






