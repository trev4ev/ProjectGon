package gameState;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class Level2State extends LevelState {

	public Level2State(GameStateManager gsm, Player p) {
		super(gsm, 2);
		this.gsm = gsm;
		this.p = p;
	}

	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if (!initialized) {
			initialized = true;
			enemies.add(new Enemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.7);

			tm.getWalls()[blocks] = enemies.get(0).getRectangle();
			blocks++;
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

	public void nextState(int i) {
		switch (i) {
		case 0:
			gsm.setLevelState(null, 1);
			break;
		case 1:
			gsm.setLevelState(new Level3State(gsm, p), 3);
			break;
		}

	}

}
