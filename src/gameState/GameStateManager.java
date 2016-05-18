package gameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

import entity.Player;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;

	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>(25);
		for(int i = 0; i < 25; i++) {
			gameStates.add(null);
		}
		currentState = MENUSTATE;
		gameStates.set(0,new MenuState(this));	
		gameStates.set(1, new Level1State(this));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}

	
	public void setLevelState(GameState gs, int i) {
		if(gameStates.get(i) == null) {
			gameStates.set(i, gs);
		}
		setState(i);
		
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed (int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
}
