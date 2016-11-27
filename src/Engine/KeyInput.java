package Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Objects.ObjectId;
import Objects.Player;
import Window.Game;
import Window.Handler;

public class KeyInput extends KeyAdapter implements KeyListener {

	public static int jumps;

	Handler handler;
	Game game = new Game();
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_E:
			Game.isPaused ^= true;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(1);
			break;
		case KeyEvent.VK_F5:
			Game.debugMode ^= true;
			break;
		}

		for (int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player && !Game.isPaused) {
				switch (key) {
				case KeyEvent.VK_D:
					tempObject.setVelX(Player.MOVE_SPEED);
					Player.facingRight = true;
					break;
				case KeyEvent.VK_A:
					tempObject.setVelX(-Player.MOVE_SPEED);
					Player.facingRight = false;
					break;
				case KeyEvent.VK_SPACE:
				case KeyEvent.VK_W:
					if (jumps < 2) 
					{
						tempObject.setJumping(true);
						tempObject.setVelY(Player.JUMP_HEIGHT);
						jumps++;
					} else 
					{
						jumps++;
						Player.canGlide = true;
					}
				}
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) 
		{
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) 
			{
				switch (key) {
				case KeyEvent.VK_D:
					if (tempObject.getVelX() > 0)
						tempObject.setVelX(0);
				case KeyEvent.VK_A:
					if (tempObject.getVelX() < 0)
						tempObject.setVelX(0);
				}
			}
		}
	}
}
