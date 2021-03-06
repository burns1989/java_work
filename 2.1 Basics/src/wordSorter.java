import java.util.ArrayList;

public class wordSorter {

	private static ArrayList<String> words = new ArrayList<String>();

	/**
	 * Prints all the values stored in the words arraylist.
	 */
	private static void printWords() {
		for (String word : words) {
			System.out.println(word);
		}
	}

	/**
	 * Splits up an input string into an arraylist of words.
	 * 
	 * @param text
	 */
	private static void splitText(String text) {
		String letter;
		String word = "";

		// Go through each char in the input text
		for (int i = 0; i < text.length(); i++) {
			letter = Character.toString(text.charAt(i)).toLowerCase();

			// If the char is a letter add it to the word
			if (letter.matches("[a-z]")) {
				word = word + letter;
			} else {
				// If it's not a letter:
				// and there is a word stored,
				if (word.length() > 0) {
					// add it to the words arrayList
					words.add(word);
					// Set "word" to blank again
					word = "";
				} else {
					// Otherwise continue to the next letter
					continue;
				}
			}
		}
	}

	private static void sortWordsAlphabetically() {
		String str1, str2;
		boolean sorted = false;

		// Keep sorting until the words are all in order
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < words.size() - 1; i++) {
				str1 = words.get(i);
				str2 = words.get(i + 1);
				
				// If the second string is alphabetically before the first swap them.
				if (str2.compareTo(str1) < 0) {
					words.set(i, str2);
					words.set(i + 1, str1);
					// Set sorted to false
					sorted = false;
				}

			}
		}
	}

	public static void main(String[] args) {
		String text = "To be or not to be, that is the question;" + "Whether `tis nobler in the mind to suffer"
				+ " the slings and arrows of outrageous fortune," + " or to take arms against a sea of troubles," + " and by opposing end them?";

		// Split up the text into words
		splitText(text);
		// Print the words
		printWords();
		// Sort the words alphabetically
		sortWordsAlphabetically();
		// Print the words after sorting them
		System.out.println("\n\nSorted words:\n");
		printWords();

	}

}
