/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Bullet class - holds the drawing and methods for Bullet
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

public class Bullet{
	
	//variables
	private int x, y; 
	private boolean colorRed, visible; 

	public Bullet(int ax, int ay) { //constructor
		x=ax+35;
		y=ay; 
		colorRed = true; 
		visible = true; 
	} //end of constructor 
	
	public void myDraw(Graphics g) {
		if(colorRed) {
			g.setColor(Color.red); //ship bullets are red 
		} else {
			g.setColor(Color.green); //alien bullets are green
		}
		g.fillRect(x, y, 5, 10); 
	}
	
	public void myMove(int move) { //move bullets
		y+=move;
	}
	
	//setter methods
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public void setVisible(boolean bool) {
		visible = bool; 
	}
	
	public void colorRed(boolean bool) {
		colorRed = bool; 
	}
	
	//getter methods
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public boolean getVisible() {
		return visible; 
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,5,10);
	}
	
	public boolean isExited(int minX, int minY, int maxX, int maxY) { //check if bullet exited the screen
		   return x>maxX || x<minX || y>maxY || y<minY;
	}
}
