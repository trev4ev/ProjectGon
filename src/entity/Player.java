package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

/**
 * The Player class represents the main character in the game. This class
 * extends Entity and has methods for movement, attacking, and drawing to the
 * screen.
 * 
 * @author Trevor Aquino
 */
public class Player extends Entity {

	/**
	 * rectangle object representing the player's attack
	 */
	private Rectangle attack;

	/**
	 * represents the last time the player attacked, the length of the attack,
	 * and the amount of time before the player can attack again
	 */
	private long attackStartTime;
	private long attackDelay;
	private long attackCooldown;

	/**
	 * represents the last time the player was hit and the amount of time before
	 * the player can be hit again
	 */
	private long hitStartTime;
	private long hitCooldown;

	/**
	 * an array of integers representing the number of sprites in each animation
	 */
	private int[] numSprites = { 7 };

	/**
	 * temporary array of sprites to create each animation
	 */
	private BufferedImage[] sprites;

	/**
	 * AudioPlayer objects to play different sounds
	 */
	private AudioPlayer death;
	private AudioPlayer hit;
	private AudioPlayer att;

	/**
	 * boolean to debug and go through the game without losing health
	 */
	private boolean invincible = false;

	/**
	 * @param tm
	 *            TileMap associated with this player
	 * @param gs
	 *            current GameState of the this player
	 */
	public Player(TileMap tm, LevelState gs) {
		super(tm, gs);

		width = 32;
		height = 32;
		cwidth = 25;
		cheight = 25;

		attackStartTime = System.nanoTime();
		attackDelay = 350;
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

		maxHealth = 10;
		health = maxHealth;

		animation = new Animation[1];

		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/attackright.gif"));
			for (int i = 0; i < animation.length; i++) {
				animation[i] = new Animation();
				sprites = new BufferedImage[numSprites[i]];
				for (int j = 0; j < sprites.length; j++) {
					sprites[j] = image.getSubimage(j * width, i * height, width, height);
				}
				animation[i].setSprites(sprites);
				animation[i].setDelay(50);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * updates the player by following an order of steps 1. find the next
	 * position for the player depending on direction it is moving 2. check if
	 * the player collides with walls or enemies and adjusts next position
	 * accordingly 3. sets the coordinates to the next position 4. determines
	 * which animation to set 5. check if player is currently attacking or
	 * cooling down from attack 6. check if player has gone through a door
	 */
	public void update() {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		if (currentFrame == 1) {
			animation[0].update();
		} else {
			animation[0].setSprite(0);
		}
		if (attacking) {
			long elapsed = (System.nanoTime() - attackStartTime) / 1000000;
			if (elapsed > attackDelay) {
				attack = null;
				attacking = false;
				currentFrame = 0;
			}
		}
		if (!canAttack) {
			long elapsed = (System.nanoTime() - attackStartTime) / 1000000;
			if (elapsed > attackCooldown) {
				canAttack = true;
			}
		}
		checkDoorCollision();
	}

	/**
	 * checks if player has collided with a door block and sends the player to
	 * the next state if it has
	 */
	public void checkDoorCollision() {
		for (int i = 0; i < tm.getDoorCount(); i++) {
			if (tm.getDoors()[i].intersects(getRectangle())) {
				if (y + tm.getTileSize() > GamePanel.HEIGHT) {
					// bottom door
					setPosition(x, height / 2);
					gs.nextState(2);
					break;
				} else if (y - tm.getTileSize() < 0) {
					// top door
					setPosition(x, GamePanel.HEIGHT - height / 2);
					gs.nextState(0);
					break;
				} else if (x + tm.getTileSize() > GamePanel.WIDTH) {
					// right door
					setPosition(width / 2, y);
					gs.nextState(1);
					break;
				} else if (x - tm.getTileSize() < 0) {
					// left door
					setPosition(GamePanel.WIDTH - width / 2, y);
					gs.nextState(3);
					break;
				}

			}
		}
	}

	/**
	 * draws the correct sprite depending on current action and direction the
	 * player is facing
	 * 
	 * @param g
	 *            Graphics object
	 */
	public void draw(Graphics2D g) {
		if (direction == 3) {
			g.drawImage(animation[0].getImage(), (int) x - width / 2 + width, (int) y - height / 2, -width, height,
					null);
		} else {
			g.drawImage(animation[0].getImage(), (int) x - width / 2, (int) y - height / 2, null);
		}
		g.setColor(Color.white);
		g.drawRect(GamePanel.WIDTH / 8 - 31, 10, maxHealth * 12 + 1, 12);
		g.setColor(Color.red);
		g.fillRect(GamePanel.WIDTH / 8 - 30, 11, health * 12, 11);
		if (invincible) {
			g.fillOval(GamePanel.WIDTH / 8 - 45, 11, 10, 10);
		}
	}

	/**
	 * checks if player has been hit recently, if not subtract 1 health. If
	 * health is at 0 end the game.
	 */
	public void hit() {
		if (!invincible) {
			if ((System.nanoTime() - hitStartTime) / 1000000 > hitCooldown) {
				hitStartTime = System.nanoTime();
				hit = new AudioPlayer("/SFX/Pain.mp3");
				hit.play();
				health--;
				if (health <= 0) {
					death = new AudioPlayer("/SFX/DeathScream.mp3");
					death.play();
					GamePanel.stopMusic();
					gs.endGame();
				}
			}
		}
	}

	/**
	 * attack in the direction which the player is currently facing and check if
	 * the attack hits any enemies
	 */
	public void attack() {
		if (!attacking && canAttack) {
			currentFrame = 1;
			attacking = true;
			canAttack = false;

			switch (direction) {
			case Entity.LEFT:
				attack = new Rectangle((int) (x - (width / 2 + 20)), (int) (y - (height / 2)), 20, height);
				break;
			case Entity.RIGHT:
				attack = new Rectangle((int) (x + (width / 2)), (int) (y - (height / 2)), 20, height);
				break;
			case Entity.UP:
				attack = new Rectangle((int) (x - (width / 2)), (int) (y - (height / 2 + 20)), width, 20);
				break;
			case Entity.DOWN:
				attack = new Rectangle((int) (x - (width / 2)), (int) (y + (height / 2)), width, 20);
				break;
			}
			for (Enemy e : gs.getEnemies()) {
				if (attack.intersects(e.getRectangle())) {
					e.hit();
				}
			}
			attackStartTime = System.nanoTime();
			att = new AudioPlayer("/SFX/Swing.mp3");
			att.play();
		}
	}

	/**
	 * depending on the key that is pressed will move the player or cause it to
	 * attack
	 * 
	 * @param k
	 *            key code
	 */
	public void keyPressed(int k) {
		switch (k) {
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
		case KeyEvent.VK_7:
			invincible = !invincible;
			break;
		}
	}

	/**
	 * stop the player from moving
	 * 
	 * @param k
	 *            key code
	 */
	public void keyReleased(int k) {
		switch (k) {
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

	/**
	 * sets the TileMap to the parameter
	 * 
	 * @param tm
	 *            new TileMap
	 */
	public void setTileMap(TileMap tm) {
		this.tm = tm;
	}

	/**
	 * sets the GameState to the parameter
	 * 
	 * @param gs
	 *            new GameState
	 */
	public void setGameState(LevelState gs) {
		this.gs = gs;
	}

}
