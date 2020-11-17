/*Nithika Karunamoorthy 
Nov/15/2020
Space Invaders: Game - main file  
ICS3U Ms.Strelkovska 
*/

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File; 

public class Game{
	public static void main(String[] args){

		new Layout(); //
		backgroundMusic();
		
	}//end of main
	
	public static void backgroundMusic() { //Plays the shooting music (code from Ms.Strelkovska)
		try {
			 Clip clip = AudioSystem.getClip();
			 clip.open(AudioSystem.getAudioInputStream(new File("background.wav"))); //get music file 
			 clip.start(); //starts music
			 clip.loop(Clip.LOOP_CONTINUOUSLY); //loops music
		} catch(Exception e) { //if there is an error
			 System.out.println("Something wrong with music player.");
		}  

    } //end of backgroundMusic() method
	
}//end of class

