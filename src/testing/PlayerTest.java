package testing;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Test;

import entity.Player;
import gameState.GameStateManager;
import gameState.Level1State;
import main.GamePanel;
import tileMap.TileMap;

public class PlayerTest {
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
		assertEquals(prehealth, p.getHealth()); // will be same health because of cooldown
	}

}
