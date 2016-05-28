package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import audio.AudioPlayer;
import gameState.GameStateManager;

/**
 * @author Trevor Aquino
 *
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	/**
	 * dimensions of the game frame and how it is scaled
	 */
	public static final int WIDTH = 600;
	public static final int HEIGHT = 480;
	public static final double SCALE = 1.4;

	/**
	 * Thread to run the game
	 */
	private Thread thread;

	/**
	 * boolean indicates that the game is running
	 */
	private boolean running;

	/**
	 * ideal frameRate
	 */
	private int FPS = 60;

	/**
	 * ideal amount of time, in milliseconds, between each update
	 */
	private long targetTime = 1000 / FPS;

	/**
	 * image to be drawn to the screen
	 */
	private BufferedImage image;

	/**
	 * Graphics object
	 */
	private Graphics2D g;

	/**
	 * AudioPlayer to start background music
	 */
	private static AudioPlayer mus;

	/**
	 * GameStateManager object to hold all game states
	 */
	private GameStateManager gsm;

	/**
	 * create a new JPanel with size properly scaled, load background music and
	 * start the music
	 */
	public GamePanel() {
		super();
		setPreferredSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
		setFocusable(true);
		requestFocus();
		mus = new AudioPlayer("/SFX/BackgroundMusic.mp3");
		mus.loop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#addNotify()
	 */
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	/**
	 * create a blank image, initialize gameStateManager and get the graphics
	 * object for the image
	 */
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}

	/**
	 * stop the background music
	 */
	public static void stopMusic() {
		mus.stop();
	}

	/**
	 * play the bakcground music
	 */
	public static void playMusic() {
		mus.loop();
	}

	/**
	 * main game loop, calls update and draw every 17 milliseconds to achieve a
	 * frame rate of 60fps
	 */
	@Override
	public void run() {
		init();

		long start;
		long elapsed;
		long wait;

		while (running) {
			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if (wait > 0) {
				try {
					Thread.sleep(wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * call the update method for the gameStateManager
	 */
	private void update() {
		gsm.update();
	}

	/**
	 * call the draw method for the gameStateManager
	 */
	private void draw() {
		gsm.draw(g);
	}

	/**
	 * draw the current image to the screen
	 */
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE), null);
		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}
}
