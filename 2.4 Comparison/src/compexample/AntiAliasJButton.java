package compexample;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * enables antialiasing
 * 
 * @author rayhe
 * 
 */
class AntiAliasJButton extends JButton {
	public static final long serialVersionUID = 62359;

	public AntiAliasJButton() {
	}

	public AntiAliasJButton(String someString) {
		super(someString);
	}

	public AntiAliasJButton(Icon someIcon) {
		super(someIcon);
	}

	public AntiAliasJButton(String someString, Icon someIcon) {
		super(someString, someIcon);
	}

	public void paintComponent(Graphics someGraphic) {
		Graphics2D myG2D = (Graphics2D) someGraphic;
		myG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(myG2D);
	}
}