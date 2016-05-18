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
		init();
	}
	
	public void init() {
		super.init();
		enemies.add(new Enemy(tm, this));
		enemies.get(0).setPosition(GamePanel.WIDTH * 0.6 , GamePanel.HEIGHT * 0.75);
	}

	
	public void nextState(int i) {
		//gsm.setLevelState(new Level2State(gsm));
	}

}
