package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import audio.AudioPlayer;
import entity.Entity;
import main.GamePanel;
import tileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;	
	private String[] options = {"Start","Help","Quit"};
	
	private Font font;
	
	private AudioPlayer aud;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/menubg.gif", 1);		
			font = new Font("Arial", Font.PLAIN, 12);
		} catch(Exception e) {
			e.printStackTrace();
		}
		GamePanel.playMusic();
	}
	
	@Override
	public void init() {}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		//bg.draw(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setFont(font);
		for(int i = 0; i < options.length; i++)
		{
			if(currentChoice == i) {
				g.setColor(new Color(102,45,145));
				g.fillOval(265, 270 + i*15, 10, 10);
			}
			g.setColor(Color.black);
			g.drawString(options[i], 280, 280 + i*15);
		}
	}
	
	private void select() {
		switch(currentChoice) {
			case 0:
				nextState(1);
				break;
			case 1:
				nextState(10);
				break;
			case 2:
				System.exit(0);
				break;
		}
	}

	@Override
	public void keyPressed(int k) {
		switch(k) {
			case KeyEvent.VK_ENTER:
				select();
				break;
			case KeyEvent.VK_DOWN:
				currentChoice++;
				if(currentChoice == options.length)
					currentChoice = 0;
				break;
			case KeyEvent.VK_UP:
				currentChoice--;
				if(currentChoice < 0)
					currentChoice = options.length - 1;
				break;
		}
	}

	@Override
	public void keyReleased(int k) {}

	@Override
	public void nextState( int i ) {
		gsm.setLevelState(null, i);
	}

	@Override
	public Entity getPlayer() {return null;}

	
}
