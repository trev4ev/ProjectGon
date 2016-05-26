package gameState;

import entity.AdvancedEnemy;
import entity.Player;
import main.GamePanel;

public class Level8State extends LevelState {

	public Level8State(GameStateManager gsm, Player p) {
		super(gsm, 8);
		this.gsm = gsm;
		this.p = p;
		p.setPosition(p.getX() - tm.getTileSize(), p.getY());
		GamePanel.stopMusic();
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
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

	public void nextState(int i) {
		switch (i) {
		case 3:
			won = true;
			break;
		}
	}
}
