package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import audio.AudioPlayer;
import entity.Enemy;
import entity.Player;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class LevelState extends GameState {
	public TileMap tm;
	public Background bg;
	public Player p;
	public ArrayList<Enemy> enemies;
	public int enemyCount;
	public boolean initialized = false;
	public boolean paused = false;
	public boolean dead = false;
	public int level;
	public int count = 0;

	public LevelState(GameStateManager gsm, int i) {
		this.gsm = gsm;
		level = i;
		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.setPosition(tm.getTileSize() * -1, tm.getTileSize() * -1);
		if (!initialized) {
			tm.loadMap("/Maps/room" + level + ".map");
			enemyCount = 0;
		} else {
			// loadSecondMap();
		}
	}

	public void pause() {
		paused = !paused;
	}

	public void endGame() {
		dead = true;
		p.update();
	}

	public void deathScreen(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, count);
		g.fillRect(0, GamePanel.HEIGHT - count, GamePanel.WIDTH, count);
		if (count <= GamePanel.HEIGHT * 1.15) {
			count+=2;
		} else {
			gsm.reset();
		}
		if (count >= GamePanel.HEIGHT / 2) {
			g.setColor(Color.white);
			g.drawString("Game Over", GamePanel.WIDTH / 2 - 30, GamePanel.HEIGHT / 2 - 10);
		}
	}

	public void removeEnemy() {
		enemyCount--;
		if (enemyCount <= 0) {
			loadSecondMap();
		}
	}

	public void loadSecondMap() {
		tm.loadMap("/Maps/room" + level + "open.map");
	}

	public void init() {
		p.setGameState(this);
		p.setTileMap(tm);
		if (!initialized) {
			bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
			enemies = new ArrayList<Enemy>();
		}
	}

	@Override
	public void update() {
		if (!dead) {
			if (!paused) {
				p.update();
				for (Enemy e : enemies) {
					e.update();
				}
			}
		}
	}

	public void nextState(int i) {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		//bg.draw(g);
		tm.draw(g);
		p.draw(g);
		for (Enemy e : enemies) {
			e.draw(g);
		}
		if (dead) {
			deathScreen(g);
		}
		if (paused) {
			// add pause
			g.fillRect(GamePanel.WIDTH / 2 - 80, GamePanel.HEIGHT / 2 - 10, 160, 20);
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

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
