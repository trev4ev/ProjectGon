package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * Represents a background for a gameState. Uses an image as a background and
 * can be moved around and drawn at different positions on the jPanel.
 * 
 * @author Trevor Aquino
 *
 */
public class Background {

	/**
	 * image which makes up the background
	 */
	private BufferedImage image;

	/**
	 * current x coordinate
	 */
	private double x;

	/**
	 * current y coordinate
	 */
	private double y;

	/**
	 * movement in the x coordinate
	 */
	private double dx;

	/**
	 * movement in the y coordinate
	 */
	private double dy;

	/**
	 * read in the file at the given path
	 * 
	 * @param s
	 *            file path to the image
	 */
	public Background(String s) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * sets the position of the background to (x, y)
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public void setPosition(double x, double y) {
		this.x = (x) % GamePanel.WIDTH;
		this.y = (y) % GamePanel.HEIGHT;
	}

	/**
	 * sets the vector to <dx, dy>
	 * 
	 * @param dx
	 *            movement in x direction
	 * @param dy
	 *            movement in y direction
	 */
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * moves the background depending on the movement vector
	 */
	public void update() {
		x += dx;
		y += dy;

		if (x < -GamePanel.WIDTH) {
			x = 0;
		}
	}

	/**
	 * draws the background at the proper location and draws extras if needed
	 * 
	 * @param g
	 *            Graphics object
	 */
	public void draw(Graphics2D g) {
		g.drawImage(image, (int) x, (int) y, null);
		if (x < 0) {
			g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
		}
		if (x > 0) {
			g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
		}
	}
}
