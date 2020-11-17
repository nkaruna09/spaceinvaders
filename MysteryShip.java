/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: MysteryShip class - holds the image and methods for MysteryShip and extends of Spaceship class
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class MysteryShip extends SpaceShip{

	//variables
	private boolean visible;  
	private ImageIcon mysteryShip = null; 

	public MysteryShip(int ax, int ay, int aw, int ah) {//constructor
		super(ax, ay, aw, ah); 
		mysteryShip = new ImageIcon("MysterySpaceShip.png");
		visible = false; 
	}//end of constructor
	
	public void myDraw(Graphics g) {
		if(visible) { //if visible is true
			g.drawImage(mysteryShip.getImage(), getX(), getY(), getWidth(), getHeight(),null);
		}
	}

	public void setVisible(boolean b) { //set visible 
		visible = b; 
	}
	
	public boolean getVisible() { //get visible 
		return visible; 
	}
	
} //end of class
