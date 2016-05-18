package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class Level1State extends GameState{
	
	private TileMap tm;
	private Background bg;
	private Player p;
	private Player p2;
	private Enemy e;
	private ArrayList<Entity> entities;
	private Rectangle playerAttack;
	private Rectangle[] enemyAttack;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		tm = new TileMap(30);
		tm.loadTiles("/Tilesets/grasstileset.gif");
		tm.loadMap("/Maps/room1.map");
		tm.setPosition(0, 0);
		p = new Player(tm, this);
		p.setPosition(GamePanel.WIDTH/2, GamePanel.HEIGHT/4);
		e = new Enemy(tm, this);
		e.setPosition(GamePanel.WIDTH*0.6, GamePanel.HEIGHT*0.75);
	}

	@Override
	public void update() {
		p.update();
		e.update();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		bg.draw(g);
		tm.draw(g);
		p.draw(g);
		e.draw(g);
	}
	
	public Rectangle getPlayerAttack() {
		return playerAttack;
	}

	public void setPlayerAttack(Rectangle playerAttack) {
		this.playerAttack = playerAttack;
	}

	@Override
	public void keyPressed(int k) {
		p.keyPressed(k);
		
	}

	@Override
	public void keyReleased(int k) {
		p.keyReleased(k);	
	}

}
