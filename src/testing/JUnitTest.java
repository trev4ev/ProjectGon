package testing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

import entity.Animation;

import tileMap.TileMap;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Test;

import entity.Player;
import gameState.GameStateManager;
import gameState.Level1State;
import main.GamePanel;

import gameState.GameStateManager;

import entity.Enemy;

/**
 * @author Alex Xu
 *
 */
public class JUnitTest {
	Animation a = new Animation();

	@Test
	public void testAnimation() {
		assertFalse(a.playedOnce());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetSprites() {
		BufferedImage[] images = new BufferedImage[1];
		a.setSprites(images);
		assertEquals(a.getSprites(), images);
	}

	@Test
	public void testSetDelay() {
		a.setDelay(1);
		assertEquals(a.getDelay(), 1);
	}

	@Test
	public void testSetSprite() {
		a.setSprite(0);
		assertEquals(a.getCurrentSprite(), 0);
	}

	TileMap tm = new TileMap(4);

	@Test
	public void testTileMap() {
		assertEquals(4, tm.getTileSize());
	}

	@Test
	public void testGetType() {
		assertEquals(tm.getType(2, 2), tm.getTiles()[2][2].getType());
	}

	@Test
	public void testSetPosition() {
		tm.setPosition(2.0, 2.0);
		assertEquals(tm.getX(), 2);
		assertEquals(tm.getY(), 2);
	}

	GamePanel gp = new GamePanel();
	Player p = new Player(new TileMap(30), new Level1State(new GameStateManager()));

	@Test
	public void testPlayer() {
		assertEquals(p.getWidth(), 32);
	}

	@Test
	public void testKeyPressed() {
		p.keyPressed(KeyEvent.VK_LEFT);
		assertEquals(p.getDirection(), 3);
	}

	@Test
	public void testSetTileMap() {
		TileMap tm = new TileMap(30);
		p.setTileMap(tm);
		assertEquals(p.getTileMap(), tm);
	}

	@Test
	public void testSetGameState() {
		Level1State gs = new Level1State(new GameStateManager());
		p.setGameState(gs);
		assertEquals(p.getGameState(), gs);
	}

	@Test
	public void testHit() {
		int prehealth = p.getHealth();
		p.hit();
		assertEquals(prehealth, p.getHealth()); // will be same health because
												// of cooldown
	}

	GameStateManager gsm = new GameStateManager();

	@Test
	public void testGameStateManager() {
		assertEquals(gsm.getGameStates().size(), 25);
	}

	@Test
	public void testSetState() {
		gsm.setState(2);
		assertEquals(2, gsm.getCurrentState());
	}

	GamePanel gp1 = new GamePanel();
	Enemy e = new Enemy(new TileMap(30), new Level1State(new GameStateManager()), 1, true);

	@Test
	public void testHitEnemy() {
		int prehealth = e.getHealth();
		e.hit();
		assertEquals(e.getHealth(), prehealth - 1);
	}

	@Test
	public void testEnemy() {
		assertTrue(e.isAlive());
	}

	@Test
	public void testSetSpeed() {
		e.setSpeed(2.0);
		assertEquals(e.getSpeed(), 2.0, 0.1);
	}

	@Test
	public void testGetBlockNum() {
		assertSame(e.getBlockNum(), 1);
	}

}
