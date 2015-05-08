/**
 * This is part of the Problem Set 0: Introduction for 6.170 Fall 2005.
 */
package lab1;

import java.util.LinkedList;

/**
 * This is a container can be used to contain Balls.
 */
public class BallContainer {

	// Contents of the box.
	LinkedList<Ball> contents;

	// Holds the volume of balls in the box.
	double volumeOfBalls;

	/**
	 * Constructor that creates a new ballcontainer.
	 */
	public BallContainer() {
		contents = new LinkedList<Ball>();
	}

	/**
	 * Adds a ball to the container. This method returns true if ball was successfully added to the container,
	 * i.e. ball is not already in the container. Of course, you are allowed to put a Ball into a container only once.
	 * Hence, this method returns false, if ball is already in the container.
	 * 
	 * @param b
	 *            Ball to be added.
	 * @return true if ball was successfully added to the container, i.e. ball is not already in the container. Returns
	 *         false, if ball is already in the container.
	 */
	public boolean add(Ball b) {
		// If the ball is already in the list return false
		if (contents.contains(b)) {
			System.out.println("The container already contains the specified ball.");
			return false;
		}
		// Otherwise add it to the list
		contents.add(b);	
		// Add the volume of the ball to the total volume
		volumeOfBalls += b.getCapacity();
		// Return true to confirm the ball was added
		return true;
	}

	/**
	 * Removes a ball from the container. This method returns true if ball was successfully removed from the
	 * container, i.e. ball is actually in the container. You cannot remove a Ball if it is not already in the container
	 * and so the method will return false, otherwise.
	 * 
	 * @param b
	 *            Ball to be removed.
	 * @return true if ball was successfully removed from the container, i.e. ball is actually in the container. Returns
	 *         false, if ball is not in the container.
	 */
	public boolean remove(Ball b) {
		// Check that the ball is actually in the list. If it's not return false
		if(!contents.contains(b)) {
			System.out.println("The container doesn't contain the specified ball.");
			return false;
		}
		// Otherwise remove the ball from the container
		contents.remove(b);		
		// Reduce the volume of balls in the container
		volumeOfBalls -= b.getCapacity();
		// Return true to confirm the ball has been removed
		return true;
	}

	/**
	 * Each Ball has a volume. This method returns the total volume of all the Balls in the container.
	 * 
	 * @return the volume of the contents of the container.
	 */
	public double getVolume() {
		return volumeOfBalls;
	}

	/**
	 * Returns the number of Balls in this container.
	 * 
	 * @return the number of Balls in this container.
	 */
	public int size() {
		return contents.size();
	}

	/**
	 * Empties the container, i.e. removes all its contents.
	 */
	public void clear() {
		// Your code goes here.
	}

	/**
	 * This method returns <tt>true</tt> if this container contains the specified Ball. It will return <tt>false</tt>
	 * otherwise.
	 * 
	 * @param b
	 *            Ball to be checked if its in container
	 * @return true if this container contains the specified Ball. Returns false, otherwise.
	 */
	public boolean contains(Ball b) {		
		// Check if the ball is already in the LinkedList
		for (Ball ball : contents) {
			// If the ball is in the list return true.
			if (ball.equals(b))
				return true;
		}
		// If it's not found return false
		return false;
	}

	protected void printBallsInContainer() {
		for (Ball ball : contents) {
			System.out.println("Ball with capacity " + ball.getCapacity());
		}
		
	}
	
}
