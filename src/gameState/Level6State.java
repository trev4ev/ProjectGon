package gameState;

import entity.AdvancedEnemy;
import entity.Player;
import main.GamePanel;

public class Level6State extends LevelState {

	public Level6State(GameStateManager gsm, Player p) {
		super(gsm, 6);
		this.gsm = gsm;
		this.p = p;
	}

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
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(1).setPosition(GamePanel.WIDTH * 0.4, GamePanel.HEIGHT * 0.7);
			enemies.get(1).setSpeed(2);
			tm.getWalls()[blocks] = enemies.get(1).getRectangle();
			blocks++;
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

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
