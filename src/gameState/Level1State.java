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

public class Level1State extends GameState{
	
	private TileMap tm;
	private Background bg;
	private Player p;
	private ArrayList<Enemy> enemies;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.loadMap("/Maps/room1.map");
		tm.setPosition(0, 0);
		p = new Player(tm, this);
		p.setPosition(GamePanel.WIDTH/2, GamePanel.HEIGHT/4);
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(tm, this));
		enemies.get(0).setPosition(GamePanel.WIDTH*0.6, GamePanel.HEIGHT*0.75);
	}

	@Override
	public void update() {
		p.update();
		for(Enemy e:enemies) {
			e.update();
		}
	}
	
	public void nextState() {
		gsm.setLevelState(new Level2State(gsm, p));
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		bg.draw(g);
		tm.draw(g);
		p.draw(g);
		for(Enemy e:enemies) {
			e.draw(g);
		}
	}

	@Override
	public void keyPressed(int k) {
		p.keyPressed(k);
		
	}

	@Override
	public void keyReleased(int k) {
		p.keyReleased(k);	
	}

}
