package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Entity;
import main.GamePanel;
import tileMap.Background;

/**
 * This class represents the menu of the game and extends GameState. The menu
 * state has 3 options which allow the users to start the game, read the help
 * manual, or exit the game.
 * 
 * @author Alex Xu
 *
 */
public class MenuState extends GameState {

	/**
	 * background object for this menuState
	 */
	private Background bg;

	/**
	 * index of the current choices
	 */
	private int currentChoice = 0;

	/**
	 * load the background image and start playing music
	 * 
	 * @param gsm
	 *            GameStateManager
	 */
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;

		try {
			bg = new Background("/Backgrounds/menubg.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		GamePanel.playMusic();
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	/**
	 * draws the background and also the three menu choices
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for (int i = 0; i < 3; i++) {
			if (currentChoice == i) {
				g.setColor(new Color(0, 0, 0, 100));
				g.fillRect(GamePanel.WIDTH / 2 - 55, 270 + i * 50, 110, 35);
			}
		}
		bg.draw(g);
	}

	/**
	 * starts game, shows help screen, or quits depending on current choice
	 */
	private void select() {
		switch (currentChoice) {
		case 0:
			nextState(1);
			break;
		case 1:
			nextState(10);
			break;
		case 2:
			System.exit(0);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		switch (k) {
		case KeyEvent.VK_ENTER:
			select();
			break;
		case KeyEvent.VK_DOWN:
			currentChoice++;
			if (currentChoice == 3)
				currentChoice = 0;
			break;
		case KeyEvent.VK_UP:
			currentChoice--;
			if (currentChoice < 0)
				currentChoice = 2;
			break;
		}
	}

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
