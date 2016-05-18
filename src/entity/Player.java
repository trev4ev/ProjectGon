package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import gameState.GameState;
import tileMap.TileMap;

public class Player extends Entity{
	
	private int health;
	private int maxHealth;
	
	private long startTime;
	private long attackDelay;
	

	public Player(TileMap tm, GameState gs) {
		super(tm, gs);
		
		width = 30;
		height = 30;
		cwidth = 25;
		cheight = 25;
		
		startTime = System.nanoTime();
		attackDelay = 150;
		
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		speed = 2;
		
		attacking = false;
		
		direction = Entity.DOWN;
		
		animation = new Animation();
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));
			int numSprites = image.getWidth() / width;
			BufferedImage[] sprites = new BufferedImage[2];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = image.getSubimage(i*width, 0, width, height);
			}
			animation.setSprites(sprites);
			animation.setDelay(400);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		animation.update();
		if(attacking) {
			long elapsed = (System.nanoTime() - startTime)/1000000;
			if(elapsed > attackDelay) {
				startTime = System.nanoTime();
				gs. = null;
				attacking = false;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), (int)x-width/2, (int)y-height/2, null);
		if(attack != null) {
			g.draw(attack);
		}
	}
	
	public void attack() {
		if(!attacking) {
			attacking = true;
			switch(direction) {
				case Entity.LEFT:
					attack = new Rectangle((int)(x-(tileSize*1.5)),(int)(y-(tileSize/2)), tileSize, tileSize);
					break;
				case Entity.RIGHT:
					attack = new Rectangle((int)(x+(tileSize/2)),(int)(y-(tileSize/2)), tileSize, tileSize);
					break;
				case Entity.UP:
					attack = new Rectangle((int)(x-(tileSize/2)),(int)(y-(tileSize*1.5)), tileSize, tileSize);
					break;
				case Entity.DOWN:
					attack = new Rectangle((int)(x-(tileSize/2)),(int)(y+(tileSize/2)), tileSize, tileSize);
					break;
			}
			startTime = System.nanoTime();
		}	
	}
	
	public void keyPressed(int k) {
		switch(k) {
			case KeyEvent.VK_LEFT:
				direction = Entity.LEFT;
				movingLeft = true;
				movingRight = false;
				break;
			case KeyEvent.VK_RIGHT:
				direction = Entity.RIGHT;
				movingRight = true;
				movingLeft = false;
				break;
			case KeyEvent.VK_UP:
				direction = Entity.UP;
				movingUp = true;
				movingDown = false;
				break;
			case KeyEvent.VK_DOWN:
				direction = Entity.DOWN;
				movingDown = true;
				movingUp = false;
				break;
			case KeyEvent.VK_SPACE:
				attack();
				break;
		}
	}
	
	public void keyReleased(int k) {
		switch(k) {
			case KeyEvent.VK_LEFT:
				movingLeft = false;
				break;
			case KeyEvent.VK_RIGHT:
				movingRight = false;
				break;
			case KeyEvent.VK_UP:
				movingUp = false;
				break;
			case KeyEvent.VK_DOWN:
				movingDown = false;
				break;
		}
	}

}
