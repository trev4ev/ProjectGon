package gameState;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class Level4State extends LevelState {

	public Level4State(GameStateManager gsm, Player p) {
		super(gsm, 4);
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
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.3);
			enemies.add(new Enemy(tm, this, blocks + 1, true));
			enemyCount++;
			enemies.get(1).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.65);

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
