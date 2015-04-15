import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Circle implements Sprite {
	
	int diameter;
	Color color;
	
	/**
	 * Create a circle filled with the color.
	 * @param diameter
	 * @param color
	 */
	public Circle(int diameter, Color color) {
		this.diameter = diameter;
		this.color = color;		
	}


	@Override
	public void draw(Graphics surface, int leftX, int topY) {
		surface.setColor(color);
		surface.fillOval(leftX, topY, diameter, diameter);
		surface.setColor(Color.BLACK);
		((Graphics2D) surface).setStroke(new BasicStroke(3.0f));
		surface.drawOval(leftX, topY, diameter, diameter);

	}

	@Override
	public int getWidth() {
		return diameter;
	}

	@Override
	public int getHeight() {
		return diameter;
	}

}
