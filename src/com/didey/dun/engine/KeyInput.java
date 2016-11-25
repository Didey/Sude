package com.didey.dun.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Objects.ObjectId;
import Objects.Player;
import Window.BufferedImageLoader;
import Window.Console;
import Window.Game;
import Window.Handler;

public class KeyInput extends KeyAdapter implements KeyListener {

	BufferedImageLoader loader = new BufferedImageLoader();

	public static int jumps;

	Handler handler;
	Game game = new Game();

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_E)
			Game.isPaused ^= true;

		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);

		if (key == KeyEvent.VK_F5 && !Game.isPaused) {
			Game.debugMode ^= true;
			if (Game.debugMode) {
				System.out.println("Debug mode has been ENABLED!");
			} else {
				System.out.println("Debug mode has been DISABLED!");
			}
		}

		if (key == KeyEvent.VK_SLASH) {
			Console.createConsole();
		}

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player && !Game.isPaused) {
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(Player.MOVE_SPEED);
					Player.facingRight = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-Player.MOVE_SPEED);
					Player.facingRight = false;
				}
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE) {
					if (jumps < 2) {
						tempObject.setJumping(true);
						tempObject.setVelY(-13);
						jumps++;
					} else {
						if (jumps < 2) {
							jumps++;
							Player.canGlide = false;
						} else {
							jumps++;
							Player.canGlide = true;
						}
					}
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D && tempObject.getVelX() > 0)
					tempObject.setVelX(0);
				if (key == KeyEvent.VK_A && tempObject.getVelX() < 0)
					tempObject.setVelX(0);
			}
		}
	}
}
