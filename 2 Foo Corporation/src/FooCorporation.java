
public class FooCorporation {
	
	public static void calculatePay (float basePay, int hoursWorked) {
		// Store the total pay
		float totalPay = 0f;
		// If pay is above minimum and hours worked are below the max.
		if (basePay >= 8f && hoursWorked <= 60) {
			// If hours is 40 or under
			if (hoursWorked <= 40) {
				totalPay = basePay*hoursWorked;
			} else if (hoursWorked > 40) {
				totalPay = (basePay*40) + (basePay*1.5f*(hoursWorked - 40));
			}		
			// Print out the total pay
			System.out.println("Total pay is: " + totalPay);
		} else if (basePay < 8f) {
			System.out.println("ERROR: The basic pay is below the minimum wage.");
		} else if (hoursWorked > 60) {
			System.out.println("ERROR: The number of hours worked is above the maximum allowed.");
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
