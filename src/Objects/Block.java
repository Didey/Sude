package Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.didey.dun.engine.GameObject;
import com.didey.dun.engine.Texture;

import Window.Game;

public class Block extends GameObject{

	Texture tex = Game.getInstance();
	private int type;
	
	
	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	
	public void tick(LinkedList<GameObject> object) { }

	public void render(Graphics g) 
	{
		// my attempt at a good rendering scheme, we can see if it works out...
		if(type == 0){
			g.drawImage(tex.block[0], (int)x, (int)y, null);
		} else {
			g.drawImage(tex.block[1], (int)x, (int)y, null);
		}
	} 


	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
