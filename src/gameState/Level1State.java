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

public class Level1State extends LevelState{
	
	public Level1State(GameStateManager gsm) {
		super(gsm, 1);
		this.gsm = gsm;
		p = new Player(tm, this);
		p.setPosition(GamePanel.WIDTH * .5, GamePanel.HEIGHT * .25);
		init();
	}
	
	public void nextState(int i) {
		gsm.setLevelState(new Level2State(gsm, p));
	}

}
