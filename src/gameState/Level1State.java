package gameState;

import entity.Player;
import main.GamePanel;

/**
 * @author Trevor Aquino
 *
 */
public class Level1State extends LevelState {

	/**
	 * create a new player and set it in the top, middle section of the screen
	 * 
	 * @param gsm
	 *            GameStatemanager
	 */
	public Level1State(GameStateManager gsm) {
		super(gsm, 1);
		p = new Player(tm, this);
		p.setPosition(GamePanel.WIDTH * .5, GamePanel.HEIGHT * .25);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.LevelState#nextState(int)
	 */
	public void nextState(int i) {
		gsm.setLevelState(new Level2State(gsm, p), 2);
	}

}
