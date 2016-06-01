package gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * The class uses an ArrayList to hold all the GameStates so they can be
 * accessed both forward and backward. Can switch from state to state easily.
 * 
 * @author Trevor Aquino
 *
 */
public class GameStateManager {

	/**
	 * ArrayList holding all the gameStates
	 */
	private ArrayList<GameState> gameStates;

	/**
	 * index of the current state
	 */
	private int currentState;

	/**
	 * static integers for the index of the menu and first level states
	 */
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;

	/**
	 * creates an ArrayList of GameStates to hold all the levels in addition to
	 * the MenuState and HelpState initializes the MenuState, HelpState and
	 * Level1State
	 */
	public GameStateManager() {
		gameStates = new ArrayList<GameState>(25);
		for (int i = 0; i < 25; i++) {
			gameStates.add(null);
		}
		currentState = MENUSTATE;
		gameStates.set(0, new MenuState(this));
		gameStates.set(1, new Level1State(this));
		gameStates.set(10, new HelpState(this));
	}

	/**
	 * change the current state to the parameter and initialize it
	 * 
	 * @param state
	 *            new state
	 */
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}

	/**
	 * called when the player loses of wins creates an ArrayList of GameStates
	 * to hold all the levels in addition to the MenuState and HelpState
	 * initializes the MenuState, HelpState and Level1State
	 */
	public void reset() {
		gameStates = new ArrayList<GameState>(25);
		for (int i = 0; i < 25; i++) {
			gameStates.add(null);
		}
		currentState = MENUSTATE;
		gameStates.set(0, new MenuState(this));
		gameStates.set(1, new Level1State(this));
		gameStates.set(10, new HelpState(this));
	}

	/**
	 * adds the LevelState to the ArrayList if it is not already there and calls
	 * setState()
	 * 
	 * @param gs
	 *            new LevelState
	 * @param i
	 *            number of the next level
	 */
	public void setLevelState(GameState gs, int i) {
		if (gameStates.get(i) == null) {
			gameStates.set(i, gs);
		}
		setState(i);

	}

	/**
	 * calls the update method for the current state
	 */
	public void update() {
		gameStates.get(currentState).update();
	}

	/**
	 * draws the current state
	 * 
	 * @param g
	 *            graphics object
	 */
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}

	/**
	 * calls the keyPressed method for the current state
	 * 
	 * @param k
	 *            keycode
	 */
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

	/**
	 * calls the keyReleased method for the current state
	 * 
	 * @param k
	 *            keycode
	 */
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

	/**
	 * @return list of game states
	 */
	public ArrayList<GameState> getGameStates() {
		return gameStates;
	}

	/**
	 * @return the index of the current state
	 */
	public int getCurrentState() {
		return currentState;
	}
}
