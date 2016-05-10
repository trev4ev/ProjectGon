package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;	
	private String[] options = {"Start","Help","Quit"};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/grassbg1.gif", 1);
			bg.setVector(-0.5,0);
			
			titleColor = Color.black;
			titleFont= new Font("Book Antiqua", Font.BOLD, 28);
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
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Project Gon", 80, 70);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++)
		{
			if(currentChoice == i)
				g.setColor(Color.white);
			else
				g.setColor(Color.green);
			g.drawString(options[i], 145, 140 + i*15);
		}
	}
	
	private void select() {
		switch(currentChoice) {
			case 0:
				gsm.setState(1);
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

	
}
