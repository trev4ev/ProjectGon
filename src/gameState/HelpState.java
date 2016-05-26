package gameState;

import java.awt.Graphics2D;

import entity.Entity;
import tileMap.Background;

public class HelpState extends GameState {
	private Background bg;

	public HelpState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/helpbg.gif", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		nextState(0);
	}

	@Override
	public void keyReleased(int k) {
	}

	@Override
	public void nextState(int i) {
		gsm.setLevelState(null, i);
	}

	@Override
	public Entity getPlayer() {
		return null;
	}

}
