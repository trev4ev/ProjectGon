package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import tileMap.TileMap;

public class Enemy extends Player{
	
	private int health;
	private int maxHealth;
	private Rectangle attack;
	
	private long startTime;
	private long moveDelay;

	public Enemy(TileMap tm) {
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 25;
		cheight = 25;
		
		startTime = System.nanoTime();
		moveDelay = 2500;
		
		movingLeft = true;
		movingRight = false;
		movingUp = false;
		movingDown = false;
		
		attacking = false;
		
		speed = 0.5;
	}
	
	public void update() {
		move();
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}
	
	public void move() {
		long elapsed = (System.nanoTime() - startTime)/1000000;
		if(elapsed > moveDelay) {
			attack();
			startTime = System.nanoTime();
			movingLeft = !movingLeft;
			movingRight = !movingRight;
		}
	}
	
	public void draw(Graphics2D g) {
		g.drawRect((int)x-width/2, (int)y - height/2, width, height);
	}

}
