import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
	ArrayList<BouncingBox> boxes = new ArrayList<BouncingBox>();

	/** Initializes this class for drawing. */
	public DrawGraphics() {
		boxes.add(new BouncingBox(100, 25, Color.BLUE));
		System.out.println("Size: " + boxes.size());
		boxes.add(new BouncingBox(200, 50, Color.RED));
		boxes.add(new BouncingBox(150, 75, Color.GREEN));
		System.out.println("Size: " + boxes.size());
		
		// Directions and speed
		int x = -1;
		int y = -1;
		
		for (int i = 0; i < boxes.size(); i++) {
			System.out.println("Run");
			boxes.get(i).setMovementVector(x, y);
			System.out.println("Size: " + boxes.size() + " i is: " + i);
			
			x+=2;
			y+=2;
		}
	}

	/** Draw the contents of the window on surface. Called 20 times per second. */
	public void draw(Graphics surface) {
		surface.drawLine(50, 50, 250, 250);
		
		// Draw an arc
		surface.drawArc(75, 75, 100, 300, 45, 35);
		
		// Draw a 3D rectangle
		surface.fill3DRect(150, 150, 20, 20, true);
		
		// Draw a filled oval
		surface.fillOval(150, 50, 50, 20);
		

		for (int i = 0; i < boxes.size(); i++) {
			boxes.get(i).draw(surface);
		}

	}
}