/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: ShieldPowerUp class - holds the image and methods for ShieldPowerUp and extends of Bullet class
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class ShieldPowerUp extends Bullet{

	//variables 
	private ImageIcon shield = null; 
	
	public ShieldPowerUp(int ax, int ay) {//constructor
		super(ax-35, ay); 
		shield = new ImageIcon("shield.png");
	}//end of constructor
	
	public void myDraw(Graphics g) {
		if(getVisible()) {
			g.drawImage(shield.getImage(), getX(), getY(), 50, 50, null);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(),getY(),50,50);
	}
}
