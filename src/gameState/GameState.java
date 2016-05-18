package gameState;

import java.awt.Graphics2D;

import entity.Entity;

public abstract class GameState {
	
	protected GameStateManager gsm;

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void nextState();
	public abstract Entity getPlayer();
	
}
