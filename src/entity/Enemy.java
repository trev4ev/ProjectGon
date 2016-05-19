package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameState.LevelState;
import main.GamePanel;
import tileMap.TileMap;

public class Enemy extends Player{
	
	private int health;
	private Rectangle attack;
	private int blockNum;

	public Enemy(TileMap tm, LevelState gs, int i) {
		super(tm, gs);
		
		blockNum = i;
		
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;
		
		movingLeft = true;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		attacking = false;
		
		speed = 1.5;
		
		health = 1;
	}
	//comment
	
	public void update() {
		if(intersects(gs.getPlayer())) {
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
			health = 1;
			gs.removeEnemy();		
		}
		
	}
	
	public void move() {
		if(x < GamePanel.WIDTH*.25 || x >GamePanel.WIDTH*.75) {
			attack();
			movingLeft = !movingLeft;
			movingRight = !movingRight;
			
		}
	}
	
	public void hit() {
		health--;
	}
	
	public void attack() {
		
	}
	
	public void draw(Graphics2D g) {
		g.drawRect((int)x-width/2, (int)y - height/2, width, height);
	}

	public int getBlockNum() {
		return blockNum;
	}

}
