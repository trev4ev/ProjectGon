package entity;

import java.awt.Rectangle;

import gameState.GameState;
import tileMap.TileMap;

public abstract class Entity {
	
	protected TileMap tm;
	protected int tileSize;
	protected GameState gs;
	
	protected double x; // refers to the middle of the character
	protected double y;
	protected double dx;
	protected double dy;
	
	protected int direction;

	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean movingUp;
	protected boolean movingDown;
	
	protected boolean attacking;
	
	protected double speed;
	
	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;
	
	protected int row;
	protected int col;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	protected Animation animation;
	protected int currentFrame;
	protected int previousFrame;
	
	public Entity(TileMap tm, GameState gs) {
		this.gs = gs;
		this.tm = tm;
		tileSize = tm.getTileSize();
		speed = 1;
	}
	
	public boolean intersects(Entity e) {
		Rectangle r1 = new Rectangle((int)x - cwidth/2, (int)y - cheight/2, cwidth, cheight);
		Rectangle r2 = e.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)(x + dx) - cwidth/2, (int)(y + dy)- cheight/2, cwidth, cheight);
	}
	
	public void checkTileMapCollision() {		
		xtemp = x;
		ytemp = y;
		
		for(int i = 0; i < tm.getWallCount(); i++) {
			if(tm.getWalls()[i].intersects(getRectangle())) {
				
				if(movingLeft) {
					dx = 0;
					movingLeft = false;
				}
				if(movingRight) {
					dx = 0;
					movingRight = false;
				}
				if(movingDown) {
					dy = 0;
					movingDown = false;
				}
				if(movingUp) {
					dy = 0;
					movingUp = false;
				}
				break;
			}
		}
		ytemp+= dy;
		xtemp+= dx;	
	}
	
	public void getNextPosition() {	
		if(movingLeft) {
			dx = -1 * speed;
		}
		else if(movingRight) {
			dx = speed;
		}
		else {
			dx = 0;
		}
		if(movingDown) {
			dy = speed;
		}
		else if(movingUp) {
			dy = -1 * speed;
		}
		else {
			dy = 0;
		}
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCwidth() {
		return cwidth;
	}

	public int getCheight() {
		return cheight;
	}
	
	public void setDirection(int dir) {
		direction = dir;
	}
	
	
}
