package entity;

import java.awt.Rectangle;

import main.GamePanel;
import tileMap.Tile;
import tileMap.TileMap;

public abstract class Entity {
	
	protected TileMap tm;
	protected int tileSize;
	
	protected double x; // refers to the middle of the character
	protected double y;
	protected double dx;
	protected double dy;
	
	protected int direction;

	public static int UP = 0;
	public static int RIGHT = 1;
	public static int DOWN = 2;
	public static int LEFT = 3;
	
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean movingUp;
	protected boolean movingDown;
	
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
	
	public Entity(TileMap tm) {
		this.tm = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects(Entity e) {
		Rectangle r1 = new Rectangle((int)x - cwidth/2, (int)y - cheight/2, cwidth, cheight);
		Rectangle r2 = e.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth/2, (int)y - cheight/2, cwidth, cheight);
	}
	
	public void checkTileMapCollision() {
		col = (int)x / tileSize;
		row = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		for(int i = 0; i < tm.getRectangleCount(); i++) {
			if(tm.getBlocks()[i].intersects(getRectangle())) {
				dx = 0;
				dy = 0;
				if(movingLeft) {
					xtemp += 3;
					movingLeft = false;
				}
				if(movingRight) {
					xtemp -= 3;
					movingRight = false;
				}
				if(movingDown) {
					ytemp -= 3;
					movingDown = false;
				}
				if(movingUp) {
					ytemp += 3;
					movingUp = false;
				}
				
			}
		}
		ytemp+= dy;
		xtemp+= dx;
		
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
