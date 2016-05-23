package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

public class Enemy extends Player{
	
	protected int health;
	protected Rectangle[] attack;
	protected int blockNum;
	
	protected long attackStartTime;
	protected long attackDelay;
	
	protected boolean horizontal;

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
		
		if(horizontal) {
			movingLeft = true;
		}
		else {
			movingUp = true;
		}
		
		attacking = false;
		
		speed = 1.5;
		
		health = 1;
	}
	
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
		if(horizontal) {
			if(x < GamePanel.WIDTH*.27 || x >GamePanel.WIDTH*.73) {
				attack();
				movingLeft = !movingLeft;
				movingRight = !movingRight;
			}
			if(x == GamePanel.WIDTH/2) {
				if((int)(Math.random()*2) == 0) {
					attack();
					movingLeft = !movingLeft;
					movingRight = !movingRight;
				}
			}
		}
		else {
			if(y < GamePanel.HEIGHT*.35 || y >GamePanel.HEIGHT*.65) {
				attack();
				movingUp = !movingUp;
				movingDown = !movingDown;
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
