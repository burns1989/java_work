package lab2;

abstract class MadLibEntry {
	// @return the value of this entry in a template
	abstract public String templateString();

	// @return the value of this entry in an actual madlib
	abstract public String madLibString();

	// Collect any relevant information from the user for this entry
	abstract public void doLib(UserInterface ui);
}

class TextEntry extends MadLibEntry {

	String textEntry;

	public TextEntry(String text) {
		textEntry = text;
	}

	@Override
	public String templateString() {
		return textEntry;
	}

	@Override
	public String madLibString() {
		return textEntry;
	}

	@Override
	public void doLib(UserInterface ui) {
	}

}

class Slot extends MadLibEntry {

	String slotEntry;
	String madLibSlotEntry;

	public Slot(String slot) {
		slotEntry = "<" + slot + ">";
	}

	@Override
	public String templateString() {
		return slotEntry;
	}

	@Override
	public String madLibString() {
		return madLibSlotEntry;
	}

	@Override
	public void doLib(UserInterface ui) {
		madLibSlotEntry = ui.promptUser("Please enter a " + slotEntry + ": ");
	}

}
