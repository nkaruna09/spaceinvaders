/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Game Layout - has three panels, 
p1 - points, lives, waves
p2 - where the game is played
p3 - exit and menu buttons
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;
import java.io.*; 
import java.util.ArrayList; 
import javax.sound.sampled.*;  

public class GameLayout extends JPanel implements ActionListener{
  
	//variables
	private JLabel l1, l2, l3, l4, l5, l6; 
	private JButton b1, b2; 
	private JPanel p1, p3; 
	static MyGameP p2; 
	static int points, wave, lives; 
    static Timer t;
   
    public GameLayout(){ //constructor
    	p1 = new JPanel();          
    	p2 = new MyGameP();
    	p3 = new JPanel();
      
    	this.setLayout(new BorderLayout(0,0));  
    
    	//setting top panel (p1)
    	this.add(p1, BorderLayout.NORTH );    
    	p1.setLayout(new FlowLayout());    
    	p1.setBackground(Color.red);
    	
    	//labels for p1
    	l1 = new JLabel("Points:        ");       
    	l2 = new JLabel(); //for number points
    	l3 = new JLabel("          Lives:        ");
    	l4 = new JLabel(); //for number of lives
    	l5 = new JLabel("          Wave:        ");
    	l6 = new JLabel(); //for number of wave
    	p1.add(l1);                       
    	p1.add(l2); 
    	p1.add(l3); 
    	p1.add(l4); 
    	p1.add(l5); 
    	p1.add(l6);
      
    	//setting center/game panel (p2)
    	this.add(p2,BorderLayout.CENTER);                          

    	//setting bottom panel (p3)
    	this.add(p3, BorderLayout.SOUTH );
    	p3.setLayout(new FlowLayout());
    	p3.setBackground(Color.black);
    	
    	//setting buttons for p3
    	b1 = new JButton("     Exit     ");
    	b2 = new JButton("     Menu     ");
    	p3.add(b1); 
    	p3.add(b2);
    	b1.addKeyListener(p2);
    	b2.addKeyListener(p2);
    	b1.addActionListener(this);
    	b2.addActionListener(this);
    }// end constructor
   
    public void actionPerformed(ActionEvent e) { //actionPerformed
    	if (e.getSource()==b1){ //if exit button is pressed
    		JOptionPane.showMessageDialog(null, "That was an exit button", "My exit message",JOptionPane.WARNING_MESSAGE ); 
    		System.exit(0); //exit game
    	} else if (e.getSource()==b2) { //if menu button is pressed
    		t.stop(); 
    		MenuPanel.onGame = false; 
    		Layout.cardsL.first(Layout.c); //switch to game panel
    	}
    }//end of actionPerformed
   
   class MyGameP extends JPanel implements ActionListener, KeyListener{ //p2
		
		//variables 
		private int velX, cnt, moveX; 
		private boolean shoot, hitBorder; 
		private SpaceShip ship = new SpaceShip(400,460,50,80); 
		private MysteryShip mysteryShip = new MysteryShip(0,15,80,50);
		private ArrayList<Enemy> alien = new ArrayList<Enemy>();
		private ArrayList<Bullet> bullet = new ArrayList<Bullet>(); 
		private ArrayList<Bullet> alienBullet = new ArrayList<Bullet>();
		private ArrayList<Barrier> barrier = new ArrayList<Barrier>();
		private ArrayList<ShieldPowerUp> shield = new ArrayList<ShieldPowerUp>();
		private ImageIcon background = null;
		private File file1 = new File("shoot.wav"); //shooting music
		private File file2 = new File("invaderkilled.wav"); //when alien dies
		private File file3 = new File("explosion.wav"); //when player's space ship is shoot
		
		public MyGameP() {//constructor
			background = new ImageIcon("background.png"); //set background image
			this.setBackground(Color.black); //set background black if image doesn't load
		    this.setLayout(null);                  
		    //setting non-static variables 
		    moveX = 3; 
		    velX = 0;
		    cnt = 1; 
		    hitBorder = false; 
		    shoot = false; 
		    //setting static variables
		    wave = 1; 
		    points = 0; 
		    lives = 3; 
		    
		    //fill alien ArrayList
		    int ax = 50; 
		    int ay = 40;  
		    for(int i=0; i<40; i++) {
		    	 alien.add(new Enemy(ax,ay)); 
		    	 ax += 70; 
		    	 if(ax >= 750) {
		    		 ax = 50; 
		    		 ay += 60; 
		    	 }
		    }
		    
		    for(int i=0; i < alien.size(); i++) { //setting the different types of aliens
		    	if(i<10) {
					alien.get(i).setEnemy(3.0); 
				} else if(i>=10 && i<20) {
					alien.get(i).setEnemy(2.0); 
				} else if(i>=20 || i<40) {
					alien.get(i).setEnemy(1.0); 
				}
		    }
		    
		    //fill b ArrayList (barrier)
		    ax = 90; 
		    ay = 400; 
		    for(int i=0; i<4; i++) {
		    	barrier.add(new Barrier(ax,ay)); 
		    	ax += 180; 
		    }
		    
		    addKeyListener(this); 
		    setFocusable(true); 
		    this.requestFocusInWindow(); 
		    
		    //timer (static variable)
		    t = new Timer(100, this); 
		    t.start();  
		} //end of constructor
		
		public void music(File f) { //Plays the music (code from Ms.Strelkovska)
			try {
				Clip clip= AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(f)); //get music file
				clip.start(); //starts music clip
			} catch(Exception e) { //if there is an error
				System.out.println("Something went wrong.");
			}  
	    } //end of music method
		 
		public void paintComponent(Graphics g){//paintComponent
		    super.paintComponent(g);    
		    Graphics2D g2 = (Graphics2D) g; 
		    
		    g.drawImage(background.getImage(),0,0,800,650,null); //draw background
		    
		    g.setColor(Color.white);
		    g.drawRect(0, 0, 800, 400); //draw white line
		    
		    for(int i=0; i<alien.size(); i++) { //draw each enemy 
		    	alien.get(i).myDraw(g); 
		    }

		    ship.myDraw(g); //draw ship 
		    mysteryShip.myDraw(g); //draw mystery ship
		    
		    for(int i=0; i<barrier.size(); i++) { //draw barrier
		    	barrier.get(i).myDraw(g);
		    }
		    
		    for(int i=0; i<shield.size(); i++) { //draw shieldPowerUp
		    	shield.get(i).myDraw(g);
		    }
		    
		    for(int i=0; i<bullet.size(); i++) { //draw bullets
		    	bullet.get(i).myDraw(g);
		    }
		    
		    for(int i=0; i<alienBullet.size(); i++) { //draw alien bullets
		    	alienBullet.get(i).colorRed(false);
		    	alienBullet.get(i).myDraw(g);
		    }
		    
		}//end of paintComponent
		
		public void reset() { //after each wave
			//non-static variables
		    moveX += 5; 
		    velX = 0;
		    cnt = 1; 
		    //static variables
		    wave += 1; 
		    l6.setText(wave+""); 
		    lives++; 
		    
		    //resetting the alien Array List
		    int ax = 50; 
		    int ay = 40;  
		    
		    if(!alien.isEmpty()) { //if list is not empty 
		    	alien.removeAll(alien); 
		    }
		    
		    for(int i=0; i<40; i++) {  //fill alien ArrayList
		    	 alien.add(new Enemy(ax,ay)); 
		    	 ax += 70; 
		    	 if(ax >= 750) {
		    		 ax = 50; 
		    		 ay += 60; 
		    	 }
		    }
		    
		    for(int i=0; i < alien.size(); i++) { //setting the different types of aliens
		    	if(i<10) {
					alien.get(i).setEnemy(3.0); 
				} else if(i>=10 && i<20) {
					alien.get(i).setEnemy(2.0); 
				} else if(i>=20 || i<40) {
					alien.get(i).setEnemy(1.0); 
				}
		    }
		    
		}
		
		public void gameOver() {
			
			//game over pop-up
			int response = JOptionPane.showConfirmDialog(null, "GAME OVER \nFinal Score: "+points+"\nFinal Wave: "+wave+" \nDo you want to play again? ","GAME OVER",JOptionPane.YES_NO_OPTION);
			
			//resetting variables
			reset(); 
			//static variables
			lives = 3; 
			l4.setText(lives+""); 
			points = 0; 
			l2.setText(points+""); 
			wave = 1; 
			l6.setText(wave+""); 
			//non-static variables
			moveX = 3; 
			ship.setShield(false);
			t.restart(); 
			
			//resetting barriers
			if(!barrier.isEmpty()) { //if list is not empty 
				barrier.removeAll(barrier); 
		    }
		    
		    int ax = 90; 
		    int ay = 400; 
		    
		    for(int i=0; i<4; i++) { //fill barrier ArrayList
		    	barrier.add(new Barrier(ax,ay)); 
		    	 ax += 180; 
		    }
		    
		    if(!shield.isEmpty()) { //if list is not empty 
		    	shield.removeAll(shield); 
		    }
		    
			if(response == JOptionPane.NO_OPTION) { //if no is pressed
				MenuPanel.onGame = false; 
				Layout.cardsL.first(Layout.c);  //switch to game panel
			}
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==t && MenuPanel.onGame) {
				
				int randomNum = (int)(Math.random()*801); //get random number from 0-800
				int size = (int)(Math.random()*alien.size()); //get random number from alien size range
				
				//printing labels in top panel (p1)
				l4.setText(lives+""); 
				l2.setText(points+""); 
				l6.setText(wave+""); 

				ship.checkBounds(); //draws ship on other side
				
				if(lives == 0) { //game over if there are no lives left
					gameOver(); 
				}
				
				for(int j=0; j<alien.size(); j++) { //game over if the aliens reach the white line
					if(lives != 0) {
						if(alien.get(j).getY()+60>400) {
							gameOver(); 
						}
					}
				} 
				
				if(alien.isEmpty()) { //start new wave if all aliens are killed
					reset(); 
				}
				
				for(int i=0; i<alien.size(); i++) { //enemy movement
					alien.get(i).setX(alien.get(i).getX()+moveX); //change alien X value
					if (alien.get(i).isExited(0,0,800,700)) { //if aliens reach the left/right border
						hitBorder = true; 
						moveX *= -1; 
					}
					if(hitBorder) { //move aliens 30 down the Y-axis
						for(int j=0; j<alien.size(); j++) {
							alien.get(j).setY(alien.get(j).getY()+30);
						}
					}
					hitBorder = false; 
					if(cnt%2 == 0) { //animation
						alien.get(i).setEnemy(alien.get(i).getEnemy()-0.1); 
					} else {
						alien.get(i).setEnemy(alien.get(i).getEnemy()+0.1);
					} 

				}
			
				//mystery ship movement and collision
				if(randomNum%20 == 0) { //show mystery ship
					mysteryShip.setVisible(true);
				}
				
				if(mysteryShip.getVisible()) { //if mystery ship is visible
					mysteryShip.setX(mysteryShip.getX()+10); //ship movement across x-axis
					for(int i=0; i<bullet.size(); i++) {
						if(mysteryShip.getX() >= 800) { //if ship exits border
							mysteryShip.setVisible(false);
							mysteryShip.setX(0); 			
						} else if(mysteryShip.getBounds().intersects(bullet.get(i).getBounds()) || bullet.get(i).getBounds().intersects(mysteryShip.getBounds())) { //if ship bullet hits mystery ship
							mysteryShip.setVisible(false);
							mysteryShip.setX(0); 
							points += randomNum; 
							l2.setText(points+""); 
							music(file2); //play invader killed music
						}
					}
				}
				
				
				//random alien bullets movement and collision 
				if(randomNum%10 == 0) {
					Enemy a = alien.get(size); 
					if(a != null && a.getAlive()) {
						alienBullet.add(new Bullet(a.getX(),a.getY()));	
					}
				} 
					
				for(int i=0; i<alienBullet.size(); i++) { //alien bullet movement
					alienBullet.get(i).myMove(9); 
					if (alienBullet.get(i).isExited(0,0,800,650)) { //if alien bullet exits border
						alienBullet.remove(i--);
					}
				}
				
				for(int i=0; i<alienBullet.size(); i++) {
					if(alienBullet.get(i).getBounds().intersects(ship.getBounds()) || ship.getBounds().intersects(alienBullet.get(i).getBounds())){
						alienBullet.remove(i--);
						if(ship.getShield()) { //if shield is up
							ship.setShield(false);
						} else {
							ship.setX(400); //set ship back to the middle
							ship.setY(460); 
							lives--; 
							l4.setText(lives+""); 
							music(file3); //play player ship destroyed
						} 
					}
				}
				
				//ship bullets movement and collision
				if(shoot && cnt%3 == 0) {
					bullet.add(new Bullet(ship.getX(),ship.getY()));	
					music(file1); //play shooting music
				}
				
				for(int i=0; i<bullet.size(); i++) { //ship bullet movement
					bullet.get(i).myMove(-20); 
					if (bullet.get(i).isExited(0,0,800,650)) { //if ship bullet exits border
						bullet.remove(i--);
					}
				}
					
				for(int i=0; i<alien.size(); i++) { //if ship bullet hits an alien
					for(int j=0; j<bullet.size(); j++) {
						if(bullet.get(j).getBounds().intersects(alien.get(i).getBounds()) || alien.get(i).getBounds().intersects(bullet.get(j).getBounds())){ 
							music(file2); //play alien killed music
							//points
					        if(alien.get(i).getEnemy() == 1.0 || alien.get(i).getEnemy() == 1.1) {
					        	points += 10; 
					        	l2.setText(points+""); 
					        } else if(alien.get(i).getEnemy() == 2.0 || alien.get(i).getEnemy() == 2.1) {
					        	points += 20; 
					        	l2.setText(points+""); 
					        } else if(alien.get(i).getEnemy() == 2.0 || alien.get(i).getEnemy() == 2.1) {
					        	points += 30; 
					        	l2.setText(points+""); 
					        }
					        	
					        bullet.remove(j--);
					        	
					        alien.get(i).setAlive(false);
						    alien.get(i).setBlast(1);
						}
					}
						
					if(alien.get(i).getBlast() == 1) { //for blast picture to stay for a second
						alien.get(i).setBlast(2); 
					} else if(alien.get(i).getBlast() == 2){
						alien.remove(i--);
					}	

				}
				
				//shield power-up movement and collision
				if(randomNum%100 == 0) {
					shield.add(new ShieldPowerUp(randomNum, 1)); 
				}
				
				for(int i=0; i<shield.size(); i++) { //shield power-up movement
					shield.get(i).myMove(10); 
					if (shield.get(i).isExited(0,0,800,650)) { //if shield power-up exits border
						shield.remove(i--);
					}
				} 
				
				for(int i=0; i<shield.size(); i++) { //if ship bullet hits shield power-up
					for(int j=0; j<bullet.size(); j++) {
						if(bullet.get(j).getBounds().intersects(shield.get(i).getBounds())){
							shield.get(i).setVisible(false); 
							bullet.remove(j--); 
							ship.setShield(true);
						}
					} 
				}
				
				for(int i=0; i<shield.size(); i++) { //remove shield power-up from list if not visible 
					if(!shield.get(i).getVisible()) {
						shield.remove(i--); 
					}
				}
				
				//barrier collision 
				for(int j=0; j<barrier.size(); j++) {
					for(int i=0; i<alienBullet.size(); i++) { //alien hits on barrier
						if(alienBullet.get(i).getBounds().intersects(barrier.get(j).getBounds()) || barrier.get(j).getBounds().intersects(alienBullet.get(i).getBounds())) {
							alienBullet.remove(i--); 
							barrier.get(j).setHits(barrier.get(j).getHits()+1);
						}
					}
					for(int i=0; i<bullet.size(); i++) { //ship hits on barrier
						if(bullet.get(i).getBounds().intersects(barrier.get(j).getBounds()) || barrier.get(j).getBounds().intersects(bullet.get(i).getBounds())) {
							bullet.remove(i--); 
							barrier.get(j).setHits(barrier.get(j).getHits()+1);
						}
					}
					
					if(barrier.get(j).getHits() >= 25) { //remove barrier after 25 hits
						barrier.remove(j--); 
					}
				} 
			
				ship.setX(ship.getX()+velX); //change x value for ship
				
				cnt++;
				repaint();
			}
		}
		
		public void keyTyped(KeyEvent e) {}
		 
		public void keyPressed(KeyEvent e) { //when key is pressed
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 65) { //left arrow key / a key
				velX = -10; 	  
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 68) { //right arrow key / d key
				velX = 10;  
			}else if(e.getKeyCode() == 32) { //space bar
				shoot = true; 
			}
		}
		   
		public void keyReleased(KeyEvent e) { //when keys are not pressed
			if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 65) { //left arrow key / a key
				velX = 0; 	  
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 68) { //right arrow key / d key
				velX = 0;  
			}else if(e.getKeyCode() == 32) { //space bar
				shoot = false; 
			}
		} 
		
   }//end of MyGameP class

}//end of GameLayout class