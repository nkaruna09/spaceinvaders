/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Barrier class - holds the drawing and methods for Barrier
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class Barrier {
	
	//variables 
	private int x, y, hits; 

	public Barrier(int ax, int ay) {//constructor
		x=ax;
		y=ay;
		hits = 0; 
	}//end of constructor
	
	public void myDraw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 70, 10); 
	}
	
	//setter methods 
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public void setHits(int h) {
		hits = h; 
	}
	
	//getter methods 
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public int getHits() {
		return hits; 
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,70,10);
	}
}
