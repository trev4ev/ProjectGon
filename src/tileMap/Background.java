package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {

	private BufferedImage image;

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

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
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
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
