import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

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
		
		for (int i = 0; i < words.size(); i++) {
			
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

	}

}
