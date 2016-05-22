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

public class LevelState extends GameState{
	public TileMap tm;
	public Background bg;
	public Player p;
	public ArrayList<Enemy> enemies;
	public int enemyCount;
	public boolean initialized = false;
	public int level;
	
	public LevelState(GameStateManager gsm, int i) {
		this.gsm = gsm;
		level = i;
		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.setPosition(tm.getTileSize() * -1, tm.getTileSize() * -1);
		if(!initialized) {
			tm.loadMap("/Maps/room" + level + ".map");
			enemyCount = 0;
		}
		else {
			//loadSecondMap();
		}		
	}
	
	public void removeEnemy() {
		enemyCount--;
		if(enemyCount <= 0) {
			loadSecondMap();
		}
	}
	
	public void loadSecondMap() {
		tm.loadMap("/Maps/room" + level + "open.map");
	}

	public void init() {
		p.setGameState(this);	
		p.setTileMap(tm);
		if(!initialized) {
			bg = new Background("/Backgrounds/grassbg1.gif",0.1);
			enemies = new ArrayList<Enemy>();
		}
	}

	@Override
	public void update() {
		p.update();
		for(Enemy e:enemies) {
			e.update();
		}
	}
	
	public void nextState(int i) {}

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
	
	public Player getPlayer() {
		return p;
	}

	@Override
	public void keyPressed(int k) {
		p.keyPressed(k);
		
	}

	@Override
	public void keyReleased(int k) {
		p.keyReleased(k);	
	}
	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
}
