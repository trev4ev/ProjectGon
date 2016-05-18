package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import entity.Entity;
import tileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;	
	private String[] options = {"Start","Help","Quit"};
	
	private Font font;
	
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/menubg.gif", 1);
			
			font = new Font("Arial", Font.PLAIN, 12);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {}

	@Override
	public void update() {
		bg.update();		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++)
		{
			if(currentChoice == i)
				g.setColor(new Color(102,45,145));
			else
				g.setColor(Color.black);
			g.drawString(options[i], 140, 140 + i*15);
		}
	}
	
	private void select() {
		switch(currentChoice) {
			case 0:
				nextState(1);
				break;
			case 1:
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
		gsm.setState(1);
	}

	@Override
	public Entity getPlayer() {return null;}

	
}
