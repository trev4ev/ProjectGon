package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Audio.AudioPlayer;
import gameState.GameState;
import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

public class Player extends Entity{
	
	private int health;
	private int maxHealth;
	private Rectangle attack;
	
	private long attackStartTime;
	private long attackDelay;
	private long attackCooldown;
	private long hitStartTime;
	private long hitCooldown;
	
	private AudioPlayer aud;

	public Player(TileMap tm, LevelState gs) {
		super(tm, gs);
		
		width = 30;
		height = 30;
		cwidth = 25;
		cheight = 25;
		
		attackStartTime = System.nanoTime();
		attackDelay = 150;
		attackCooldown = 650;
		
		hitStartTime = System.nanoTime();
		hitCooldown = 800;
		
		movingLeft = false;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		speed = 4;
		
		attacking = false;
		canAttack = true;
		
		direction = Entity.DOWN;
		
		maxHealth = 5;
		health = maxHealth;
		
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
			long elapsed = (System.nanoTime() - attackStartTime)/1000000;
			if(elapsed > attackDelay) {
				attack = null;
				attacking = false;
			}
		}
		if(!canAttack) {
			long elapsed = (System.nanoTime() - attackStartTime)/1000000;
			if(elapsed > attackCooldown) {
				canAttack = true;			
			}
		}
		checkDoorCollision();
	}
	
	public void checkDoorCollision() {		
		for(int i = 0; i < tm.getDoorCount(); i++) {
			if(tm.getDoors()[i].intersects(getRectangle())) {
				if(y + tm.getTileSize() > GamePanel.HEIGHT) {
					//bottom door
					setPosition(x,height/2);
					gs.nextState(2);
					break;
				}
				else if(y - tm.getTileSize() < 0) {
					//top door
					setPosition(x,GamePanel.HEIGHT - height/2);
					gs.nextState(0);
					break;
				}
				else if(x + tm.getTileSize() > GamePanel.WIDTH) {
					//right door
					setPosition(width/2,y);
					gs.nextState(1);
					break;
				}
				else if(x - tm.getTileSize() < 0) {
					//left door
					setPosition(GamePanel.WIDTH - width/2,y);
					gs.nextState(3);
					break;
				}
				
				
			}
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), (int)x-width/2, (int)y-height/2, null);
		g.setColor(Color.black);
		g.drawRect(GamePanel.WIDTH/8 - 31, 9, maxHealth * 12 + 1, 11);
		g.setColor(Color.red);
		g.fillRect(GamePanel.WIDTH/8 - 30, 10, health * 12, 10);
		if(attack != null) {
			g.draw(attack);
		}
	}
	
	public void hit() {
		if((System.nanoTime() - hitStartTime)/1000000 > hitCooldown) {
			hitStartTime = System.nanoTime();
			health--;
			if(health <= 0) {			
				aud = new AudioPlayer("/SFX/DeathScream.mp3");
				aud.play();
				gs.endGame();
			}
		}

	}
	
	public void attack() {
		if(!attacking && canAttack) {
			attacking = true;
			canAttack = false;
			switch(direction) {
//				case Entity.LEFT:
//					attack = new Rectangle((int)(x-(tileSize*1.5)),(int)(y-(tileSize/2)), tileSize, tileSize);
//					break;
//				case Entity.RIGHT:
//					attack = new Rectangle((int)(x+(tileSize/2)),(int)(y-(tileSize/2)), tileSize, tileSize);
//					break;
//				case Entity.UP:
//					attack = new Rectangle((int)(x-(tileSize/2)),(int)(y-(tileSize*1.5)), tileSize, tileSize);
//					break;
//				case Entity.DOWN:
//					attack = new Rectangle((int)(x-(tileSize/2)),(int)(y+(tileSize/2)), tileSize, tileSize);
//					break;
				case Entity.LEFT:
					attack = new Rectangle((int)(x-(width/2 + 20)),(int)(y-(height/2)), 20, height);
					break;
				case Entity.RIGHT:
					attack = new Rectangle((int)(x+(width/2)),(int)(y-(height/2)), 20, height);
					break;
				case Entity.UP:
					attack = new Rectangle((int)(x-(width/2)),(int)(y-(height/2 + 20)), width, 20);
					break;
				case Entity.DOWN:
					attack = new Rectangle((int)(x-(width/2)),(int)(y+(height/2)), width, 20);
					break;
			}
			for(Enemy e: gs.getEnemies()) {
				if(attack.intersects(e.getRectangle())) {
					e.hit();
				}
			}
			attackStartTime = System.nanoTime();
			
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
			case KeyEvent.VK_P:
				gs.pause();
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

	public void setTileMap(TileMap tm) {
		this.tm = tm;		
	}

	public void setGameState(LevelState gs) {
		this.gs = gs;
	}

}
