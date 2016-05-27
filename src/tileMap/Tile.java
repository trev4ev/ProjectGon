package tileMap;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private int type;

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	public static final int DOOR = 2;

	/**
	 * creates a new Tile object
	 * 
	 * @param image
	 *            the image for this specific tile
	 * @param type
	 *            the type of tile: door, regular, or wall
	 */
	public Tile(BufferedImage image, int type) {
		this.type = type;
		this.image = image;
	}

	/**
	 * @return image of this tile
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return type of this tile
	 */
	public int getType() {
		return type;
	}

}
