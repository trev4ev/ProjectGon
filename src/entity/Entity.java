package entity;

import java.awt.Rectangle;

import gameState.LevelState;
import tileMap.TileMap;

/**
 * This class is the parent to Player, Enemy, and AdvancedEnemy. This class
 * holds methods and variables for position, direction, health, speed, width and
 * height
 * 
 * @author Jerry Qing
 *
 */
public abstract class Entity {

	/**
	 * TileMap which this entity is a part of
	 */
	protected TileMap tm;

	/**
	 * size of each tile
	 */
	protected int tileSize;

	/**
	 * Current levelState of this entity
	 */
	protected LevelState gs;

	/**
	 * x coordinate of the middle of the entity
	 */
	protected double x;

	/**
	 * y coordinate of the middle of the entity
	 */
	protected double y;

	/**
	 * movement in x direction
	 */
	protected double dx;

	/**
	 * movement in y direction
	 */
	protected double dy;

	/**
	 * integer representing which direction entity is facing
	 */
	protected int direction;

	/**
	 * static integers for direction
	 */
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;

	/**
	 * booleans to indicate if the entity is moving in these directions
	 */
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean movingUp;
	protected boolean movingDown;

	/**
	 * boolean to indicate if the entity is currently attacking and/or has
	 * attacked recently
	 */
	protected boolean attacking;
	protected boolean canAttack;

	/**
	 * boolean to indicate if entity is alive
	 */
	protected boolean isAlive;

	/**
	 * the speed at which the entity moves
	 */
	protected double speed;

	/**
	 * current health and max health of the entity
	 */
	protected int health;
	protected int maxHealth;

	/**
	 * width and height of the entity image
	 */
	protected int width;
	protected int height;

	/**
	 * width and height of the entity hit box
	 */
	protected int cwidth;
	protected int cheight;

	/**
	 * where the entity will be based on dx and dy
	 */
	protected double xtemp;
	protected double ytemp;

	/**
	 * array of animation objects for entity
	 */
	protected Animation[] animation;

	/**
	 * index of the current animation
	 */
	protected int currentFrame;

	/**
	 * Constructs a new Entity and sets the TileMap and GameState
	 * 
	 * @param tm
	 *            TileMap to be set for this entity
	 * @param gs
	 *            GameState to be set for this entity
	 */
	public Entity(TileMap tm, LevelState gs) {
		this.gs = gs;
		this.tm = tm;
		tileSize = tm.getTileSize();
		speed = 1;
	}

	/**
	 * checks if this entity intersects with another entity using the rectangle
	 * intersects method
	 * 
	 * @param e
	 *            entity to check with
	 * @return true if intersects with entity e
	 */
	public boolean intersects(Entity e) {
		Rectangle r1 = new Rectangle((int) x - cwidth / 2, (int) y - cheight / 2, cwidth, cheight);
		Rectangle r2 = e.getRectangle();
		return r1.intersects(r2);
	}

	/**
	 * @return Rectangle based on the entity's current coordinates
	 */
	public Rectangle getRectangle() {
		return new Rectangle((int) (x + dx) - cwidth / 2, (int) (y + dy) - cheight / 2, cwidth, cheight);
	}

	/**
	 * check if the entity collides with any walls and stops the entity from
	 * moving if it does
	 */
	public void checkTileMapCollision() {
		xtemp = x;
		ytemp = y;

		for (int i = 0; i < tm.getWallCount(); i++) {
			if (tm.getWalls()[i].intersects(getRectangle())) {
				if (movingLeft) {
					dx = 0;
					movingLeft = false;
				}
				if (movingRight) {
					dx = 0;
					movingRight = false;
				}
				if (movingDown) {
					dy = 0;
					movingDown = false;
				}
				if (movingUp) {
					dy = 0;
					movingUp = false;
				}
				break;
			}
		}
		ytemp += dy;
		xtemp += dx;
	}

	/**
	 * sets dx and dy depending on what the current direction movement of the
	 * entity is
	 */
	public void getNextPosition() {
		if (movingLeft) {
			dx = -1 * speed;
		} else if (movingRight) {
			dx = speed;
		} else {
			dx = 0;
		}
		if (movingDown) {
			dy = speed;
		} else if (movingUp) {
			dy = -1 * speed;
		} else {
			dy = 0;
		}
	}

	/**
	 * @param x
	 *            new x coordinate
	 * @param y
	 *            new y coordinate
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param dx
	 *            new dx movement
	 * @param dy
	 *            new dy movement
	 */
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * @return the current x value
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * @return the current y value
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * @return the width of the entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height of the entity
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the collision width of the entity
	 */
	public int getCwidth() {
		return cwidth;
	}

	/**
	 * @return the collision height of the entity
	 */
	public int getCheight() {
		return cheight;
	}

	/**
	 * @param dir
	 *            new direction to be set
	 */
	public void setDirection(int dir) {
		direction = dir;
	}

}
