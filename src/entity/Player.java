package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import tileMap.TileMap;

public class Player extends Entity{
	
	private int health;
	private int maxHealth;

	public Player(TileMap tm) {
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
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
	}
	
	public void getNextPosition() {	
		if(movingLeft) {
			dx = -1;
		}
		else if(movingRight) {
			dx = 1;
		}
		else {
			dx = 0;
		}
		if(movingDown) {
			dy = 1;
		}
		else if(movingUp) {
			dy = -1;
		}
		else {
			dy = 0;
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), (int)x-width/2, (int)y-height/2, null);
		g.draw(getRectangle());
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
