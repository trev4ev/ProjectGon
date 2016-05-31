package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.Enemy;
import gameState.GameStateManager;
import gameState.Level1State;
import main.GamePanel;
import tileMap.TileMap;

public class EnemyTest {
	GamePanel gp = new GamePanel();
	Enemy e = new Enemy(new TileMap(30), new Level1State(new GameStateManager()), 1, true);

	@Test
	public void testHit() {
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
