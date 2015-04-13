public class Marathon {

	/**
	 * Returns the index of the fastest time in an input array.
	 * 
	 * @param times
	 * @return the index of the fastest time.
	 */
	public static int getFastestTimeIndex(int[] times) {
		int fastestTime = times[0];
		int fastestTimeIndex = 0;

		for (int i = 1; i < times.length; i++) {
			if (times[i] < fastestTime) {
				fastestTime = times[i];
				fastestTimeIndex = i;
			}
		}
		return fastestTimeIndex;
	}

	/**
	 * Returns the index of the second fastest time in an input array.
	 * 
	 * @param times
	 * @return the index of the fastest time.
	 */
	public static int getSecondFastestTimeIndex(int[] times) {
		int fastestTimeIndex = getFastestTimeIndex(times);
		int secondFastestTimeIndex = 0;
		int secondFastestTime = 5000;

		for (int i = 0; i < times.length; i++) {
			if (times[i] < secondFastestTime && i != fastestTimeIndex) {
				secondFastestTime = times[i];
				secondFastestTimeIndex = i;
			}
		}
		
		return secondFastestTimeIndex;
	}

	public static void main(String[] args) {

		String[] names = { "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron", "Kate" };
		int[] times = { 341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299, 343, 317, 265 };

		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i] + ": " + times[i]);
		}

		int index = getFastestTimeIndex(times);
		System.out.println("Fastest runner: " + names[index] + ". Time: " + times[index]);
		
		int index2 = getSecondFastestTimeIndex(times);
		System.out.println("2nd fastest runner: " + names[index2] + ". Time: " + times[index2]);
	}

}
