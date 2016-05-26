package gameState;

import java.awt.Graphics2D;

import entity.Entity;
import tileMap.Background;

public class HelpState extends GameState {
	private Background bg;

	/**
	 * sets the background to the help image
	 * 
	 * @param gsm
	 *            GameStateManager controlling this GameState
	 */
	public HelpState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/helpbg.gif", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#init()
	 */
	@Override
	public void init() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#update()
	 */
	@Override
	public void update() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

	/**
	 * no matter what key is pressed, go back to the menu
	 * 
	 * @param k
	 *            keycode
	 */
	@Override
	public void keyPressed(int k) {
		nextState(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#nextState(int)
	 */
	@Override
	public void nextState(int i) {
		gsm.setLevelState(null, i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#getPlayer()
	 */
	@Override
	public Entity getPlayer() {
		return null;
	}

}
