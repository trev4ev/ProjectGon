package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import gameState.GameState;
import main.GamePanel;
import tileMap.TileMap;

public class Enemy extends Player{
	
	private int health;
	private Rectangle attack;

	public Enemy(TileMap tm, GameState gs) {
		super(tm, gs);
		
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;
		
		movingLeft = true;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		attacking = false;
		
		speed = 0.5;
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
			tm.getWalls()[tm.getWallCount() - 1] = getRectangle();
			setPosition(xtemp, ytemp);
		}
		
	}
	
	public void move() {
		if(x < GamePanel.WIDTH*.25 || x >GamePanel.WIDTH*.75) {
			attack();
			movingLeft = !movingLeft;
			movingRight = !movingRight;
			
		}
	}
	
	public void attack() {
		
	}
	
	public void draw(Graphics2D g) {
		g.drawRect((int)x-width/2, (int)y - height/2, width, height);
	}

}
