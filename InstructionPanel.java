/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Instructions Panel - shows the instructions for the game and has "Play Game" button and "Menu" button
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

public class InstructionPanel extends JPanel implements ActionListener{
	
	//variables
	private JButton btn1, btn2; 
	private ImageIcon background = null;
	private ImageIcon instructions = null;
	private ImageIcon movement = null;
	private ImageIcon points = null;
	private ImageIcon planet = null;
	
	public InstructionPanel() {
		this.setLayout(new FlowLayout()); //set layout to FlowLayout
		
		background = new ImageIcon("background.png"); //set background image
		instructions = new ImageIcon("InstructionsWriting.png"); //set instructions image
		movement = new ImageIcon("InstructionsMovement.png"); //set movenment image
		points = new ImageIcon("InstructionsPoints.png"); //set points image
		planet = new ImageIcon("planet.jpg"); //set planet image
		
		//setting btn1 and btn2
		btn1 = new JButton(new ImageIcon("Menu.png")); //set menu button 
		btn2 = new JButton(new ImageIcon("PlayGame.png")); //set play button
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn1.addKeyListener(GameLayout.p2);
		btn2.addKeyListener(GameLayout.p2);
		this.add(btn1); 
		this.add(btn2); 
		btn1.setFocusable(false);
		btn2.setFocusable(false);
		
		this.setBackground(Color.black); //set background black if image doesn't load
	}
	
	public void paintComponent(Graphics g){
	    super.paintComponent(g);     
	    g.drawImage(background.getImage(),0,0,800,650,null); //draw background image
		g.drawImage(planet.getImage(),350,210,450,300,null); //draw background image
		g.drawImage(instructions.getImage(),25,25,400,300,null); //draw background image
		g.drawImage(movement.getImage(),25,325,420,100,null); //draw background image
		g.drawImage(points.getImage(),450,25,300,200,null); //draw background image
			
	    btn1.setLocation(50,500); //set location of btn1
	    btn2.setLocation(500,500); //set location of btn2
	 }
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn1) { //if menu button is pressed
			Layout.cardsL.previous(Layout.c); //switch to menu panel
		} else if (e.getSource() == btn2) { //if play button is pressed
			MenuPanel.onGame = true; 
			Layout.cardsL.last(Layout.c); //switch to game panel
			Layout.gameP.p2.setFocusable(true);
			Layout.gameP.p2.requestFocusInWindow();
			GameLayout.t.start();
		}
	}
	
}
