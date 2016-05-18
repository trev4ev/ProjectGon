package gameState;

import entity.Enemy;
import entity.Player;
import main.GamePanel;

public class Level3State extends LevelState{

	public Level3State(GameStateManager gsm, Player p) {
		super(gsm, 3);
		this.gsm = gsm;
		this.p = p;
		init();
	}
	
	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		enemies.add(new Enemy(tm, this, blocks));
		enemies.get(0).setPosition(GamePanel.WIDTH * 0.6 , GamePanel.HEIGHT * 0.75);
		tm.getWalls()[blocks] = enemies.get(0).getRectangle();
		blocks++;
		enemies.add(new Enemy(tm, this, blocks));
		enemies.get(1).setPosition(GamePanel.WIDTH * 0.6 , GamePanel.HEIGHT * 0.5);
		tm.getWalls()[blocks] = enemies.get(0).getRectangle();
		blocks++;
		tm.setWallCount(blocks);
		for(int i = tm.getWallCount(); i < tm.getWalls().length; i++){
			tm.getWalls()[i] = null;
		}
	}

	
	public void nextState(int i) {
		tm.setWallCount(tm.getWallCount() - 2);
		//gsm.setLevelState(new Level3State(gsm, p));
	}
}
