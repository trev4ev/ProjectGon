package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

public class Enemy extends Player {

	/**
	 * an array of rectangles which represent the enemy's attacks
	 */
	protected Rectangle[] attack;
	/**
	 * unique number to represent this enemy
	 */
	protected int blockNum;

	/**
	 * time since last attack
	 */
	protected long attackStartTime;
	/**
	 * amount of time to wait before attacking again
	 */
	protected long attackDelay;

	/**
	 * indicates if the enemy moves vertical or horizontal
	 */
	protected boolean horizontal;

	/**
	 * creates a new Enemy and initializes its width, height, and direction
	 * depending on parameters given
	 * 
	 * @param tm
	 *            current TileMap
	 * @param gs
	 *            current GameState
	 * @param i
	 *            the block number associated with this enemy
	 * @param direction
	 *            true if moving horizontal
	 */
	public Enemy(TileMap tm, LevelState gs, int i, boolean direction) {
		super(tm, gs);

		horizontal = direction;

		isAlive = true;

		attack = new Rectangle[4];

		blockNum = i;

		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;

		attackStartTime = System.nanoTime();
		attackDelay = 400;

		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;

		if (horizontal) {
			movingLeft = true;
		} else {
			movingUp = true;
		}

		attacking = false;

		speed = 2;

		health = 1;
	}

	/**
	 * @param x
	 *            new speed
	 */
	public void setSpeed(double x) {
		speed = x;
	}

	/**
	 * updates the enemy by going through certain steps 1. check if the enemy is
	 * still alive 2. check if the enemy is attacking 3. check if the enemy
	 * intersects with a player, if so attack 4. move the enemy to the next
	 * space depending on its direction 5. check if enemy is dead and if so,
	 * remove from game
	 */
	public void update() {
		if (isAlive) {
			if (attacking) {
				long elapsed = (System.nanoTime() - attackStartTime) / 1000000;
				if (elapsed > attackDelay) {
					for (Rectangle r : attack) {
						r = null;
					}
					attacking = false;
				}
			} else if (intersects(gs.getPlayer())) {
				attack();
			} else {
				move();
				getNextPosition();
				xtemp = x + dx;
				ytemp = y + dy;
				tm.getWalls()[blockNum] = getRectangle();
				setPosition(xtemp, ytemp);
			}
			if (health <= 0) {
				x = tileSize * -1;
				y = tileSize * -1;
				isAlive = false;
				for (Rectangle r : attack) {
					r = null;
				}
				attacking = false;
				tm.getWalls()[blockNum] = getRectangle();
				gs.removeEnemy();
			}
		}
	}

	/**
	 * depending on whether or not the enemy moves horizontal, change the
	 * direction of movement whenever the enemy is not within the middle half of
	 * the TileMap
	 */
	public void move() {
		if (horizontal) {
			if (x < GamePanel.WIDTH * .27 || x > GamePanel.WIDTH * .73) {
				attack();
				movingLeft = !movingLeft;
				movingRight = !movingRight;
			}
			if (x == GamePanel.WIDTH / 2) {
				if ((int) (Math.random() * 2) == 0) {
					attack();
					movingLeft = !movingLeft;
					movingRight = !movingRight;
				}
			}
		} else {
			if (y < GamePanel.HEIGHT * .35 || y > GamePanel.HEIGHT * .65) {
				attack();
				movingUp = !movingUp;
				movingDown = !movingDown;
			}
			if (x == GamePanel.HEIGHT / 2) {
				if ((int) (Math.random() * 2) == 0) {
					attack();
					movingUp = !movingUp;
					movingDown = !movingDown;
				}
			}
		}
	}

	/**
	 * subtract health by one
	 */
	public void hit() {
		health--;
	}

	/**
	 * attack in all four directions and check if attacks intersect any players,
	 * if so damage the player
	 */
	public void attack() {
		if (!attacking) {
			attacking = true;
			attack[0] = new Rectangle((int) (x - (width * 1.5)), (int) y - (height / 2), width, height);
			attack[1] = new Rectangle((int) (x + (width * 0.5)), (int) y - (height / 2), width, height);
			attack[2] = new Rectangle((int) (x - (width / 2)), (int) (y - (height * 1.5)), width, height);
			attack[3] = new Rectangle((int) (x - (width / 2)), (int) (y + (height / 2)), width, height);

			for (Rectangle r : attack) {
				if (r.intersects(gs.getPlayer().getRectangle())) {
					gs.getPlayer().hit();
				}
			}
			attackStartTime = System.nanoTime();
		}
	}

	/**
	 * draw the enemy in its current position and its attacks if attacking
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.drawRect((int) x - width / 2, (int) y - height / 2, width, height);
		if (attacking) {
			g.setColor(Color.green);
			for (Rectangle r : attack) {
				g.draw(r);
			}
		}
	}

	/**
	 * @return the block number for this enemy
	 */
	public int getBlockNum() {
		return blockNum;
	}

}
