package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class Level2State extends LevelState{
	
	public Level2State(GameStateManager gsm, Player p) {
		super(gsm, 2);
		this.gsm = gsm;
		this.p = p;
	}
	
	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if(!initialized) {
			initialized = true;
			enemies.add(new Enemy(tm, this, blocks));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6 , GamePanel.HEIGHT - (tm.getTileSize()*1.5 + 10));
		}
		tm.getWalls()[blocks] = enemies.get(0).getRectangle();
		blocks++;
		tm.setWallCount(blocks);
		for(int i = tm.getWallCount(); i < tm.getWalls().length; i++){
			tm.getWalls()[i] = null;
		}
	}

	
	public void nextState(int i) {
		switch(i) {
			case 0:
				gsm.setLevelState(null, 1);
				break;
			case 1:
				gsm.setLevelState(new Level3State(gsm, p), 3);
				break;
		}
		
	}

}
