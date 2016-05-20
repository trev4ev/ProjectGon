package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

public class Enemy extends Player{
	
	private int health;
	private Rectangle[] attack;
	private int blockNum;
	
	private long attackStartTime;
	private long attackDelay;

	public Enemy(TileMap tm, LevelState gs, int i) {
		super(tm, gs);
		
		isAlive = true;
		
		attack = new Rectangle[4];
		
		blockNum = i;
		
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;
		
		attackStartTime = System.nanoTime();
		attackDelay = 300;
		
		movingLeft = true;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		attacking = false;
		
		speed = 2;
		
		health = 1;
	}
	//comment
	
	public void update() {
		if(isAlive) {
			if(attacking) {
				long elapsed = (System.nanoTime() - attackStartTime)/1000000;
				if(elapsed > attackDelay) {
					for(Rectangle r: attack) {
						r = null;
					}
					attacking = false;
				}
			}
			else if(intersects(gs.getPlayer())) {
				attack();
			}
			else {
				move();
				getNextPosition();
				xtemp = x + dx;
				ytemp = y + dy;
				tm.getWalls()[blockNum] = getRectangle();
				setPosition(xtemp, ytemp);
			}
			if(health <= 0) {
				x = tileSize * -1;
				y = tileSize * -1;
				isAlive = false;
				for(Rectangle r: attack) {
					r = null;
				}
				attacking = false;
				tm.getWalls()[blockNum] = getRectangle();
				gs.removeEnemy();
			}
		}
	}
	
	public void move() {
		if(x < GamePanel.WIDTH*.25 || x >GamePanel.WIDTH*.75) {
			attack();
			movingLeft = !movingLeft;
			movingRight = !movingRight;
		}
		if(x == GamePanel.WIDTH/2) {
			if((int)(Math.random()*2) == 0) {
				movingLeft = !movingLeft;
				movingRight = !movingRight;
			}
		}
	}
	
	public void hit() {
		health--;
	}
	
	public void attack() {
		if(!attacking) {
			attacking = true;
			attack[0] = new Rectangle((int) (x - (width*1.5)), (int)y-(height/2), width, height);
			attack[1] = new Rectangle((int) (x + (width*0.5)), (int)y-(height/2), width, height);
			attack[2] = new Rectangle((int)(x-(width/2)),(int)(y-(height*1.5)), width, height);
			attack[3] = new Rectangle((int)(x-(width/2)),(int)(y+(height/2)), width, height);
			
			for(Rectangle r: attack) {
				if(r.intersects(gs.getPlayer().getRectangle())) {
					gs.getPlayer().hit();
				}
			}
			attackStartTime = System.nanoTime();
		}
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.drawRect((int)x-width/2, (int)y - height/2, width, height);
		if(attacking) {
			g.setColor(Color.green);
			for(Rectangle r:attack) {
				g.draw(r);
			}
		}
	}

	public int getBlockNum() {
		return blockNum;
	}

}
