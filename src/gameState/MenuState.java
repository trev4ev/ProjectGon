package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Entity;
import main.GamePanel;
import tileMap.Background;

/**
 * @author Trevor Aquino
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
	 * array of possible menu choices
	 */
	private String[] options = { "Start", "Help", "Quit" };

	/**
	 * font for menu options
	 */
	private Font font;

	/**
	 * load the background image and start playing music
	 * 
	 * @param gsm
	 *            GameStateManager
	 */
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;

		try {
			bg = new Background("/Backgrounds/menubg.gif");
			font = new Font("Arial", Font.PLAIN, 12);
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
		// bg.draw(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (currentChoice == i) {
				g.setColor(Color.red);
				g.fillOval(GamePanel.WIDTH/2 - 34, 270 + i * 15, 10, 10);
			}
			g.setColor(Color.black);
			g.drawString(options[i], GamePanel.WIDTH/2 - 20, 280 + i * 15);
		}
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
			if (currentChoice == options.length)
				currentChoice = 0;
			break;
		case KeyEvent.VK_UP:
			currentChoice--;
			if (currentChoice < 0)
				currentChoice = options.length - 1;
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
