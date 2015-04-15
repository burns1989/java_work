import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
	ArrayList<Mover> sprites = new ArrayList<Mover>();

	/** Initializes this class for drawing. */
	public DrawGraphics() {
		Rectangle box = new Rectangle(15, 20, Color.RED);
		sprites.add(new Bouncer(100, 170, box));
		sprites.get(0).setMovementVector(3, 1);

		Circle circle = new Circle(20, Color.BLUE);
		sprites.add(new Bouncer(10, 20, circle));
		sprites.get(1).setMovementVector(-1, 3);

		Rectangle box1 = new Rectangle(15, 20, Color.RED);
		sprites.add(new StraightMover(10, 17, box1));
		sprites.get(2).setMovementVector(3, 1);

		Circle circle1 = new Circle(20, Color.BLUE);
		sprites.add(new StraightMover(100, 200, circle1));
		sprites.get(3).setMovementVector(-1, 3);
	}

	/** Draw the contents of the window on surface. */
	public void draw(Graphics surface) {
		for (Mover sprite : sprites) {
			sprite.draw(surface);
		}
	}
}