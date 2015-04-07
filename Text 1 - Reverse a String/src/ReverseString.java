
public class ReverseString {

	public static void main(String[] args) {

		ReverseString reverseString = new ReverseString();
		
		// Print out the manual reverse
		System.out.println(reverseString.manualReverse("brian"));
		
		//Print out the automatic reverse
		System.out.println(reverseString.autoReverse("brian"));
	}

	// Manually code the reversal of a string
	public String manualReverse(String string){
		String reverse = "";
		int length = string.length();
		// For each letter add it to the reverse string.
		for (int i = 0; i < length; i++) {
			reverse = reverse + string.charAt(length - i - 1);
		}
		
		return reverse;		
	}
	
	// Reverse using the StringBuffer class
	public String autoReverse(String string) {
		StringBuffer buff = new StringBuffer(string);
		return buff.reverse().toString();
	}

}
