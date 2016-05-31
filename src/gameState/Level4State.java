package gameState;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

/**
 * Extends levelState and adds all enemies. Sends the player to the next level
 * depending on what door they go through.
 * 
 * @author Trevor Aquino
 *
 */
public class Level4State extends LevelState {

	/**
	 * calls the super constructor
	 * 
	 * @param gsm
	 *            GameStateManager
	 * @param p
	 *            Player
	 */
	public Level4State(GameStateManager gsm, Player p) {
		super(gsm, 4);
		this.gsm = gsm;
		this.p = p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.LevelState#init()
	 */
	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if (!initialized) {
			initialized = true;
			enemies.add(new Enemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.25);
			enemies.add(new Enemy(tm, this, blocks + 1, true));
			enemyCount++;
			enemies.get(1).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.75);

			tm.getWalls()[blocks] = enemies.get(0).getRectangle();
			blocks++;
			tm.getWalls()[blocks] = enemies.get(0).getRectangle();
			blocks++;
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.LevelState#nextState(int)
	 */
	public void nextState(int i) {
		switch (i) {
		case 1:
			gsm.setLevelState(new Level5State(gsm, p), 5);
			break;
		case 2:
			gsm.setLevelState(null, 3);
			break;
		}
	}
}
