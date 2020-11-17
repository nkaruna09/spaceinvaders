/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: SpaceShip class - holds the image and methods for spaceship
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class SpaceShip{
	
	//variables 
	private int x, y, w, h;
	private boolean shield; 
	private ImageIcon ship = null; 

	public SpaceShip(int ax, int ay, int aw, int ah) { //constructor
		ship = new ImageIcon("ship.png");
		x=ax;
		y=ay;
		w=aw;
		h=ah;
		shield = false; 
	} //end of constructor
	
	public void myDraw(Graphics g) {
		g.drawImage(ship.getImage(), x, y, w, h,null); //draw ship image
		if(shield) { //draw shield if true
			g.setColor(Color.cyan);
			g.drawOval(x, y, w, h);
		}
	}
	
	//setter methods
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public void setShield(boolean s) { 
		shield = s; 
	}
	
	public void setVisible(boolean b) { }
	
	//getter methods 
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public int getWidth() {
		return w; 
	}
	
	public int getHeight() {
		return h; 
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,50,80);
	}
	
	public boolean getShield() { 
		return shield; 
	}
	
	public boolean getVisible() { 
		return true; 
	}
	
	public void checkBounds() { //changes x value if ship goes of the screen
		if(getX()>800) {
			setX(-50);
		}
		
		if(getX()<-50) {
			setX(800); 
		}
	}

} //end of class
