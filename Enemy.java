/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Enemy class - holds the image and methods for Enemy 
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class Enemy{
	
	//variables
	private int x, y, w, h, blast;
	private double enemy; 
	private boolean alive; 
	private ImageIcon enemy1_1 = null; 
	private ImageIcon enemy1_2 = null; 
	private ImageIcon enemy1_Blast = null;
	private ImageIcon enemy2_1 = null; 
	private ImageIcon enemy2_2 = null; 
	private ImageIcon enemy2_Blast = null;
	private ImageIcon enemy3_1 = null; 
	private ImageIcon enemy3_2 = null; 
	private ImageIcon enemy3_Blast = null;

	public Enemy(int ax, int ay) { //contructor
		//set images 
		enemy1_1 = new ImageIcon("Enemy1_1.png");
		enemy1_2 = new ImageIcon("Enemy1_2.png");
		enemy1_Blast = new ImageIcon("Enemy1_Blast.png");
		enemy2_1= new ImageIcon("Enemy2_1.png");
		enemy2_2= new ImageIcon("Enemy2_2.png");
		enemy2_Blast = new ImageIcon("Enemy2_Blast.png");
		enemy3_1= new ImageIcon("Enemy3_1.png");
		enemy3_2= new ImageIcon("Enemy3_2.png");
		enemy3_Blast = new ImageIcon("Enemy3_Blast.png");
		//set variables 
		x=ax;
		y=ay;
		w=70;
		h=60;
		enemy = 1.0; 
		alive = true; 
		blast = 0; 
	} //end of constructor 
	
	public void myDraw(Graphics g) {
			if(alive) { //if enemy is alive
				if(enemy == 1.0) { 
					g.drawImage(enemy1_1.getImage(), x, y, w, h,null); 
				}else if(enemy == 1.1) {
					g.drawImage(enemy1_2.getImage(), x, y, w, h,null);
				}else if(enemy == 2.0) {
					g.drawImage(enemy2_1.getImage(), x, y, w, h,null);
				}else if(enemy == 2.1) {
					g.drawImage(enemy2_2.getImage(), x, y, w, h,null);
				}else if(enemy == 3.0) {
					g.drawImage(enemy3_1.getImage(), x, y, w, h,null);
				}else if(enemy == 3.1) {
					g.drawImage(enemy3_2.getImage(), x, y, w, h,null);
				}
			}else{ //if enemy is not alive
				if(enemy == 1.0 || enemy == 1.1) {
					g.drawImage(enemy1_Blast.getImage(), x, y, w, h,null);
				} else if(enemy == 2.0 || enemy == 2.1) {
					g.drawImage(enemy2_Blast.getImage(), x, y, w, h,null);
				} else if(enemy == 3.0 || enemy == 3.1) {
					g.drawImage(enemy3_Blast.getImage(), x, y, w, h,null);
				}
			}
	}
	
	//setter methods 
	public void setX(int x) {
		this.x = x; 
	}
	
	public void setY(int y) {
		this.y = y; 
	}
	
	public void setEnemy(double w) {
		enemy = w; 
	}
	
	public void setAlive(boolean a) {
		alive = false; 
	}
	
	public void setBlast(int a) {
		blast = a; 
	}
	
	//getter methods
	public int getX() {
		return x; 
	}
	
	public int getY() {
		return y; 
	}
	
	public double getEnemy() {
		return enemy; 
	}
	
	public boolean getAlive() {
		return alive; 
	}
	
	public int getBlast() {
		return blast; 
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,w,h);
	}
	
	public boolean isExited(int minX, int minY, int maxX, int maxY) { //check if enemy exited the screen
		return x+35>maxX || x+35<minX || y>maxY || y<minY;
	}
} //end of class
