

public class PigLatin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PigLatin pigLatin = new PigLatin();
		
		System.out.println(pigLatin.convertWord("brian"));
		System.out.println(pigLatin.convertWord("egg"));
		
	}
	
	//
	public String convertWord(String word) {
		String pigLatin = "";
		
		if (isVowel(word.charAt(0))) {
			pigLatin = word + "way";
		} else {			
			pigLatin = word.substring(1) + word.charAt(0) + "ay";
		}
		
		return pigLatin;
	}
	
	// Will return true or false depending on whether or not the input letter is a vowel
	public boolean isVowel(char ch) {
		ch = Character.toLowerCase(ch);
		// 'a' == 'a' will return true
		return ch == 'a' | ch == 'e' | ch == 'i' | ch == 'o' | ch == 'u';
	}

}
