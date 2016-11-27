package Window;

import java.awt.Color;
import java.awt.Graphics;

// Class to handle all graphical events on the screen not related to the game(pause menu, timer, etc).
public class GUI {
	
	private final String PAUSE_MESSAGE = "Game has been paused.";
	private final Color pauseTint = new Color(0, 0, 0, 127);
	
	public GUI() { }
	
	// All of the logic for the GUI in game. Where it goes relative to the player, Camera, and perhaps other entities.
	public void guiRender(Graphics g) {
		if(Game.isPaused)
		{
			g.setColor(Color.BLACK);
			g.drawString(PAUSE_MESSAGE, Game.camWidth/2, Game.camHeight/2);
			g.setColor(pauseTint);
			g.fillRect(0, 0, Game.camWidth, Game.camHeight);			
		}
	}
	
	
}
