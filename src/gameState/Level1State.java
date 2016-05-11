package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class Level1State extends GameState{
	
	private TileMap tm;
	private Background bg;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.loadMap("/Maps/level1-1.map");
		tm.setPosition(0, 0);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		bg.draw(g);
		tm.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
