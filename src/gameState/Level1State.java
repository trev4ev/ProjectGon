package gameState;

import entity.Player;
import main.GamePanel;

public class Level1State extends LevelState {

	public Level1State(GameStateManager gsm) {
		super(gsm, 1);
		p = new Player(tm, this);
		p.setPosition(GamePanel.WIDTH * .5, GamePanel.HEIGHT * .25);
		init();
	}

	public void nextState(int i) {
		gsm.setLevelState(new Level2State(gsm, p), 2);
	}

}
