public class CountVowels {

	public int aCount, eCount, iCount, oCount, uCount, consonants;

	public static void main(String[] args) {

		CountVowels countVowels = new CountVowels();
		
		countVowels.countVowels("This old man. He played one. He played knick knack on my door. Up");
		
		countVowels.printResults();

	}
	
	public void CountVowels() {
		aCount = eCount = iCount = oCount = uCount = 0;
	}

	public void countVowels(String passage) {
		for (int i = 0; i < passage.length(); i++) {

			switch (passage.toLowerCase().charAt(i)) {
			case 'a':
				aCount++;
				break;
			case 'e':
				eCount++;
				break;
			case 'i':
				iCount++;
				break;
			case 'o':
				oCount++;
				break;
			case 'u':
				uCount++;
				break;
			default:
				consonants++;
				break;
			}
		}
	}
	
	public void printResults() {
		System.out.println("a " + aCount);
		System.out.println("e " + eCount);
		System.out.println("i " + iCount);
		System.out.println("o " + oCount);
		System.out.println("u " + uCount);
		System.out.println("consonants " + consonants);
	}
}
