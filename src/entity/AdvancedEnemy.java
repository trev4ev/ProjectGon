package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

/**
 * This class extends Enemy but has a more advanced and random movement algorithm.
 * 
 * @author Jerry Qing
 *
 */
public class AdvancedEnemy extends Enemy {

	/**
	 * time since last changed direction
	 */
	private long moveStartTime;

	/**
	 * amount of time to wait before changing direction
	 */
	private long moveDelay;

	/**
	 * Constructs an Enemy object which has all the same methods but has a more
	 * random, and therefore less predictable, movement algorithm
	 * 
	 * @param tm
	 *            TileMap to be assigned to this enemy
	 * @param gs
	 *            Current GameState
	 * @param i
	 *            BlockNumber given to this enemy
	 * @param direction
	 *            moving horizontal or vertical
	 */
	public AdvancedEnemy(TileMap tm, LevelState gs, int i, boolean direction) {
		super(tm, gs, i, direction);
		moveDelay = 1000;
		moveStartTime = System.nanoTime();
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/enemy2.png"));
			attackImage = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/enemyattack2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * depending on whether or not the enemy moves horizontal, change the
	 * direction of movement whenever the enemy is not within the middle half of
	 * the TileMap
	 */
	@Override
	public void move() {
		long elapsed = (System.nanoTime() - moveStartTime) / 1000000;
		if (elapsed > moveDelay) {
			if (Math.random() > 0.5) {
				attack();
				movingLeft = false;
				movingRight = false;
				movingUp = false;
				movingDown = false;
				switch ((int) (Math.random() * 4)) {
				case 0:
					movingLeft = true;
					break;
				case 1:
					movingRight = true;
					break;
				case 2:
					movingUp = true;
					break;
				case 3:
					movingDown = true;
					break;
				}
			}
			moveStartTime = System.nanoTime();
		}
		if (x < GamePanel.WIDTH * 0.2) {
			movingLeft = false;
			movingRight = true;
		}
		if (x > GamePanel.WIDTH * 0.8) {
			movingLeft = true;
			movingRight = false;
		}
		if (y < GamePanel.HEIGHT * 0.2) {
			movingUp = false;
			movingDown = true;
		}
		if (y > GamePanel.HEIGHT * 0.8) {
			movingDown = false;
			movingUp = true;
		}
	}

}
