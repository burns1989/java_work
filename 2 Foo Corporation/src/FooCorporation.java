
public class FooCorporation {
	
	public static void calculatePay (float basePay, int hoursWorked) {
		// Store the total pay
		float totalPay = 0f;
		// If pay is below minimum print an error
		if (basePay < 8f) {
			System.out.println("ERROR: The basic pay is below the minimum wage.");
		} else if (hoursWorked > 60) {
			// If hours worked is over max allowed print an error.
			System.out.println("ERROR: The number of hours worked is above the maximum allowed.");
		} else {
			// Otherwise calculate the pay.
			if (hoursWorked <= 40) {
				totalPay = basePay*hoursWorked;
			} else if (hoursWorked > 40) {
				totalPay = (basePay * 40) + (basePay * 1.5f * (hoursWorked - 40));
			}		
			// Print out the total pay
			System.out.println("Total pay is: " + totalPay);
		} 
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		calculatePay(7.5f, 35);
		calculatePay(8.2f, 47);
		calculatePay(10f, 73);
	}

}
