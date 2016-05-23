package gameState;

import entity.AdvancedEnemy;
import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class Level5State extends LevelState{

	public Level5State(GameStateManager gsm, Player p) {
		super(gsm, 5);
		this.gsm = gsm;
		this.p = p;
	}
	
	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if(!initialized) {
			initialized = true;
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6 , GamePanel.HEIGHT * 0.5);
			tm.getWalls()[blocks] = enemies.get(0).getRectangle();
			blocks++;
			tm.setWallCount(blocks);
		}	
		for(int i = tm.getWallCount(); i < tm.getWalls().length; i++){
			tm.getWalls()[i] = null;
		}
	}

	
	public void nextState(int i) {
		switch(i) {
		case 1:
			//gsm.addLevelState(new Level1State(gsm));
			break;
		case 3:
			gsm.setLevelState(null, 4);
			break;
	}
	}
}
