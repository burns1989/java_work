import java.awt.Graphics;


public interface Mover {
	
	/** Starts moving the object in the direction (xIncrement, yIncrement). */
	public void setMovementVector(int xIncrement, int yIncrement);

	public void draw(Graphics graphics);

}
