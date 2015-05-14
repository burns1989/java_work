package compexample;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * enables antialiasing
 * 
 * @author rayhe
 * 
 */

class AntiAliasJLabel extends JLabel {
	public static final long serialVersionUID = 62359;

	public AntiAliasJLabel() {
	}

	public AntiAliasJLabel(String someString) {
		super(someString);
	}

	public AntiAliasJLabel(Icon icon) {
		super(icon);
	}

	public void paintComponent(Graphics someGraphic) {
		Graphics2D myG2D = (Graphics2D) someGraphic;
		myG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(myG2D);
	}
}