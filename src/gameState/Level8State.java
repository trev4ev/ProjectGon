package gameState;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class Level8State extends LevelState {

	public Level8State(GameStateManager gsm, Player p) {
		super(gsm, 8);
		this.gsm = gsm;
		this.p = p;
	}

	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if (!initialized) {
			initialized = true;
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

	public void nextState(int i) {
		switch (i) {
		case 1:
			// gsm.addLevelState(new Level1State(gsm));
			break;
		case 3:
			// gsm.setLevelState(null, 5);
			break;
		}
	}
}
