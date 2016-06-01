package gameState;

import audio.AudioPlayer;
import entity.AdvancedEnemy;
import entity.Player;
import main.GamePanel;

/**
 * Extends levelState and adds all enemies. Sends the player to the next level
 * depending on what door they go through.
 * @author Trevor Aquino
 *
 */
public class Level8State extends LevelState {
	
	private AudioPlayer vic;

	/**
	 * calls the super constructor, stops the current background music
	 * 
	 * @param gsm
	 *            GameStateManager
	 * @param p
	 *            Player
	 */
	public Level8State(GameStateManager gsm, Player p) {
		super(gsm, 8);
		this.gsm = gsm;
		this.p = p;
		p.setPosition(p.getX() - tm.getTileSize(), p.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.LevelState#init()
	 */
	public void init() {
		super.init();
		int blocks = tm.getWallCount();
		if (!initialized) {
			initialized = true;
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(0).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.5);
			enemies.get(0).setSpeed(4);
			tm.getWalls()[blocks] = enemies.get(0).getRectangle();
			blocks++;
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(1).setPosition(GamePanel.WIDTH * 0.4, GamePanel.HEIGHT * 0.7);
			enemies.get(1).setSpeed(2);
			tm.getWalls()[blocks] = enemies.get(1).getRectangle();
			blocks++;
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(2).setPosition(GamePanel.WIDTH * 0.4, GamePanel.HEIGHT * 0.4);
			enemies.get(2).setSpeed(2);
			tm.getWalls()[blocks] = enemies.get(2).getRectangle();
			blocks++;
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(3).setPosition(GamePanel.WIDTH * 0.6, GamePanel.HEIGHT * 0.4);
			enemies.get(3).setSpeed(4);
			tm.getWalls()[blocks] = enemies.get(3).getRectangle();
			blocks++;
			
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(4).setPosition(GamePanel.WIDTH * 0.5, GamePanel.HEIGHT * 0.5);
			enemies.get(4).setSpeed(4);
			tm.getWalls()[blocks] = enemies.get(3).getRectangle();
			blocks++;
			
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(5).setPosition(GamePanel.WIDTH * 0.2, GamePanel.HEIGHT * 0.8);
			enemies.get(5).setSpeed(4);
			tm.getWalls()[blocks] = enemies.get(3).getRectangle();
			blocks++;
			
			enemies.add(new AdvancedEnemy(tm, this, blocks, true));
			enemyCount++;
			enemies.get(6).setPosition(GamePanel.WIDTH * 0.8, GamePanel.HEIGHT * 0.2);
			enemies.get(6).setSpeed(4);
			tm.getWalls()[blocks] = enemies.get(3).getRectangle();
			blocks++;
			tm.setWallCount(blocks);
		}
		for (int i = tm.getWallCount(); i < tm.getWalls().length; i++) {
			tm.getWalls()[i] = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameState.LevelState#nextState(int)
	 */
	public void nextState(int i) {
		switch (i) {
		case 3:
			won = true;
			GamePanel.stopMusic();
			vic = new AudioPlayer("/SFX/Victory.mp3");
			vic.play();
			break;
		}
	}
}
