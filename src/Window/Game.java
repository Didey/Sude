package Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.didey.dun.engine.KeyInput;
import com.didey.dun.engine.Texture;

import Objects.ObjectId;

public class Game extends Canvas implements Runnable {

	public static boolean debugMode = false;
	public static boolean entityGravity = true;
	private boolean running = false;
	public static boolean isPaused = false;

	private Thread thread;

	public static int camWidth;
	public static int camHeight;
	public int WIDTH, HEIGHT;
	public static int fps;
	public static int ticks;

	public BufferedImage level = null;

	public static BufferedImage logo;

	// TODO: Make all static variables accessible without fucking static.

	Handler handler;
	public Camera cam;
	static Texture tex;

	Random rand = new Random();

	public static int LEVEL = 1;

	private void init() {

		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		level = loader.loadImage("/level.png");
		logo = loader.loadImage("/logo.png");
		
		cam = new Camera(0, 0);
		handler = new Handler(cam);
		cam = new Camera(0, 0);

		handler.LoadImageLevel(level);

		this.addKeyListener(new KeyInput(handler));

	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				fps = frames;
				ticks = updates;
				frames = 0;
				updates = 0;
			}
		}

	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////
		// Camera code

		camWidth = getWidth();
		camHeight = getHeight();

		
		 g.setColor(new Color(91, 140, 245));
		 g.fillRect(0, 0, getWidth(), getHeight());
		 
		 
		// Camera control
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());

		/////////////////////////////////////////////

		g.dispose();
		bs.show();
	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String args[]) {
		new Window(800, 600, "Duck Parkour: Alpha 0.1!", new Game());
		Console.displayConsole();
	}

}