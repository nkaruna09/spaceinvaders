/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Layout class - sets up the CardLayout for the program 
ICS3U Ms.Strelkovska 
*/

import java.awt.*; 
import javax.swing.*; 

public class Layout extends JFrame{
	
	//variables 
	static CardLayout cardsL; 
	static Container c; 
	static JFrame f; 
	
	static MenuPanel menuP;  
	static GameLayout gameP;   
	static InstructionPanel instructionP;
	
	public Layout() { //constructor 

		c = this.getContentPane(); //get container
		cardsL = new CardLayout(); 
		c.setLayout(cardsL); //set Layout to CardLayout
		
		//create panel objects
		instructionP = new InstructionPanel(); 
		menuP = new MenuPanel();
		gameP = new GameLayout();
		
		//add panel objects to CardLayout
		c.add("Menu", menuP);
		c.add("Instructions", instructionP);
		c.add("Game", gameP);
		
		Layout.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
		this.setVisible(true);     // make the frame visible
		this.setSize(800, 650);    // set the size of the frame
		this.setResizable(false);  // set resizable false
	
	} //end of constructor
	
}//end of class