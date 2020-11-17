/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Menu Panel - opens when game runs, has "Play Game" button and "Instructions" button
ICS3U Ms.Strelkovska 
*/
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

public class MenuPanel extends JPanel implements ActionListener{
	
	//variables 
	private JButton btn1, btn2; 
	static boolean onGame; 
	private ImageIcon title = null;
	private ImageIcon background = null;
	
	public MenuPanel() { //constructor 
	
		this.setLayout(new FlowLayout());//set layout to FlowLayout
		
		title = new ImageIcon("menuTitle.png"); //set title image
		background = new ImageIcon("background.png"); //set background image
		
		//setting btn1 and btn2
		btn1 = new JButton(new ImageIcon("PlayGame.png")); //set play button
		btn2 = new JButton(new ImageIcon("Instructions.png")); //set instructions button
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn1.addKeyListener(GameLayout.p2);
		btn2.addKeyListener(GameLayout.p2);
		this.add(btn1); 
		this.add(btn2); 
		btn1.setFocusable(false);
		btn2.setFocusable(false);
		
		this.setBackground(Color.black); //set background black if image doesn't load
		onGame = false;
	} //end constructor 
	
	public void paintComponent(Graphics g){ //paintComponent
	     super.paintComponent(g);       
	     g.drawImage(background.getImage(),0,0,800,650,null); //draw background image
	     g.drawImage(title.getImage(),150,0,500,250,null); //draw title image
	    
	     btn1.setLocation(290,300); //set location of btn1
	     btn2.setLocation(290,420); //set location of btn2
	     
	} //end of paintComponent
	
	public void actionPerformed(ActionEvent e) { //actionPerformed
		if(e.getSource()==btn1) { //if play button is pressed
			onGame = true; 
			Layout.cardsL.last(Layout.c); //switch to game panel
			Layout.gameP.p2.setFocusable(true);
			Layout.gameP.p2.requestFocusInWindow();
			GameLayout.t.start(); 
		 } else if(e.getSource()==btn2) { //if instructions button is pressed
			Layout.cardsL.next(Layout.c); //switch to instructions panel
		 }   
	} //end of actionPerformed
	
} //end of class