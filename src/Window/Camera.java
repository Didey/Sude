package Window;

import com.didey.dun.engine.GameObject;

public class Camera {

	private float x, y;

	public int camX;
	public int camY;

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject Player) {

//		x += ((-Player.getX() + Game.camWidth / 2) - x) * 0.0400;
//		y += ((-Player.getY() + Game.camHeight / 2) - y) * 0.0700;

		x += ((-Player.getX() + Game.camWidth / 2) - x) * 1;
		y += ((-Player.getY() + Game.camHeight / 2) - y) * 1;

	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
