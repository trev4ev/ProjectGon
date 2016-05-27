package gameState;

import entity.AdvancedEnemy;
import entity.Player;
import main.GamePanel;

public class Level6State extends LevelState {

	/**
	 * calls the super constructor
	 * 
	 * @param gsm
	 *            GameStateManager
	 * @param p
	 *            Player
	 */
	public Level6State(GameStateManager gsm, Player p) {
		super(gsm, 6);
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
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.5);
			enemies.get(0).setSpeed(4);
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
		case 2:
			gsm.setLevelState(new Level7State(gsm, p), 7);
			break;
		case 3:
			gsm.setLevelState(null, 5);
			break;
		}
	}
}
