package Window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.didey.dun.engine.GameObject;

import Objects.Block;
import Objects.ObjectId;
import Objects.Player;
import Objects.Sign;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	private Game game = new Game();
	
	private Camera cam;

	private BufferedImage level2 = null;
	
	public Handler(Camera cam){
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level2 = loader.loadImage("/level2.png");
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.tick(object);
		}
	} 
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	
	public void LoadImageLevel(BufferedImage image) {
		int levelWidth = image.getWidth();
		int levelHeight = image.getHeight();

		System.out.println("width, height " + levelWidth + " " + levelHeight);
		// Sweet algorithm for generating levels from pictures, thanks based RealTutsGML.
		for (int xx = 0; xx < levelHeight; xx++) {
			for (int yy = 0; yy < levelWidth; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 & blue == 255)
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block)); // grass

				if (red == 128 && green == 128 & blue == 128)
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block)); // dirt

				if (red == 0 && green == 0 & blue == 255)
					addObject(new Player(xx * 32, yy * 32, this, cam, ObjectId.Player));

				if (red == 255 && green == 0 & blue == 0)
					addObject(new Sign(xx * 32, yy * 32, "Test #1", ObjectId.Sign));

				if (red == 255 && green == 255 & blue == 0)
					addObject(new Sign(xx * 32, yy * 32, "Test #2", ObjectId.Sign));

			}
		}

	}
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void switchLevel(){
		clearLevel();
		cam.setX(0);
		
		// Load levels.
		switch(Game.LEVEL){
		case 1:
			LoadImageLevel(level2);
			break;
		}

		Game.LEVEL++;
	}
	
	private void clearLevel() {
		this.object.clear();
	}
}