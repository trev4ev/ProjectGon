package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entity.Enemy;
import entity.Player;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

/**
 * @author Trevor Aquino
 *
 */
public class LevelState extends GameState {

	/**
	 * TileMap for this levelState
	 */
	public TileMap tm;

	/**
	 * background for this levelState
	 */
	public Background bg;

	/**
	 * the player object in this levelState
	 */
	public Player p;

	/**
	 * an arrayList of all enemies added to this levelState
	 */
	public ArrayList<Enemy> enemies;

	/**
	 * a count of all enemies alive
	 */
	public int enemyCount;

	/**
	 * boolean indicating if this levelState has been initialized
	 */
	public boolean initialized = false;

	/**
	 * boolean indicating if the game is paused
	 */
	public boolean paused = false;

	/**
	 * booleans indicating if the player has died or won the game
	 */
	public boolean dead = false;
	public boolean won = false;

	/**
	 * integer describiing which level
	 */
	public int level;

	/**
	 * basic counter for end game
	 */
	public int count = 0;

	/**
	 * create and new TileMap and load the map corresponding to the current
	 * level
	 * 
	 * @param gsm
	 *            gameStateManager
	 * @param i
	 *            the current level
	 */
	public LevelState(GameStateManager gsm, int i) {
		this.gsm = gsm;
		level = i;
		tm = new TileMap(32);
		tm.loadTiles("/Tilesets/tileset.png");
		tm.setPosition(tm.getTileSize() * -1, tm.getTileSize() * -1);
		if (!initialized) {
			tm.loadMap("/Maps/room" + level + ".map");
			enemyCount = 0;
		}
	}

	/**
	 * pause or upause the game
	 */
	public void pause() {
		paused = !paused;
	}

	/**
	 * end the game
	 */
	public void endGame() {
		dead = true;
		p.update();
	}

	/**
	 * this method gets called when the player dies, uses a counter to make
	 * black screen appear and eventually go back to the menu state
	 * 
	 * @param g
	 *            graphics object
	 */
	public void deathScreen(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.WIDTH, count);
		g.fillRect(0, GamePanel.HEIGHT - count, GamePanel.WIDTH, count);
		if (count <= GamePanel.HEIGHT * 1.15) {
			count += 2;
		} else {
			count = 0;
			gsm.reset();
		}
		if (count >= GamePanel.HEIGHT / 2) {
			g.setColor(Color.white);
			g.drawString("Game Over", GamePanel.WIDTH / 2 - 30, GamePanel.HEIGHT / 2 - 10);
		}
	}

	/**
	 * this method gets called when the player wins, uses a counter to show the
	 * win screen and eventually go back to the menu state
	 * 
	 * @param g
	 *            graphics object
	 */
	public void winScreen(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.black);
		g.drawString("You Win", GamePanel.WIDTH / 2 - 30, GamePanel.HEIGHT / 2 - 10);
		if (count <= GamePanel.HEIGHT) {
			count += 2;
		} else {
			count = 0;
			gsm.reset();
		}
	}

	/**
	 * remove enemy and open the door if all enemies have been killed
	 */
	public void removeEnemy() {
		enemyCount--;
		if (enemyCount <= 0) {
			loadSecondMap();
		}
	}

	/**
	 * load the map with the door opened
	 */
	public void loadSecondMap() {
		tm.loadMap("/Maps/room" + level + "open.map");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#init()
	 */
	public void init() {
		p.setGameState(this);
		p.setTileMap(tm);
		if (!initialized) {
			bg = new Background("/Backgrounds/indoorbg.jpg");
			enemies = new ArrayList<Enemy>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#update()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#nextState(int)
	 */
	public void nextState(int i) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		if (!paused) {
			g.setColor(Color.white);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
			bg.draw(g);
			tm.draw(g);
			p.draw(g);
			for (Enemy e : enemies) {
				e.draw(g);
			}
			if (dead) {
				deathScreen(g);
			}
			if (won) {
				winScreen(g);
			}

		} else {
			// add pause
			g.fillRect(GamePanel.WIDTH / 2 - 80, GamePanel.HEIGHT / 2 - 10, 160, 20);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#getPlayer()
	 */
	public Player getPlayer() {
		return p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#keyPressed(int)
	 */
	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_P) {
			paused = !paused;
		}
		if (!paused) {
			p.keyPressed(k);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.GameState#keyReleased(int)
	 */
	@Override
	public void keyReleased(int k) {
		p.keyReleased(k);
	}

	/**
	 * @return ArrayList of all enemies in the current level
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
}
