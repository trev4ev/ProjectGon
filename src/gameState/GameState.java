package gameState;

import java.awt.Graphics2D;

import entity.Entity;

/**
 * Abstract class for all GameStates in the game. Includes the necessary methods
 * including initialization, update, and drawing to the screen.
 * 
 * @author Alex Xu
 *
 */
public abstract class GameState {

	/**
	 * the GameStateManager managing this gameState
	 */
	protected GameStateManager gsm;

	/**
	 * set the proper variables and initialize necessary backgrounds and
	 * entities
	 */
	public abstract void init();

	/**
	 * update all items in the GameState including entities, tileMaps, and
	 * backgrounds
	 */
	public abstract void update();

	/**
	 * draw all items in the GameState including entities, tileMaps, and
	 * backgrounds
	 * 
	 * @param g
	 *            Graphics object
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * call the keyPressed method for the player
	 * 
	 * @param k
	 *            keycode
	 */
	public abstract void keyPressed(int k);

	/**
	 * call the keyReleased method for the player
	 * 
	 * @param k
	 *            keycode
	 */
	public abstract void keyReleased(int k);

	/**
	 * load the next game state
	 * 
	 * @param i
	 *            the direction which the player has left
	 */
	public abstract void nextState(int i);

	/**
	 * @return the current Player
	 */
	public abstract Entity getPlayer();

}
