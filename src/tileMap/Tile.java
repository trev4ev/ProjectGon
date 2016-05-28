package tileMap;

import java.awt.image.BufferedImage;

public class Tile {

	/**
	 * image of tile
	 */
	private BufferedImage image;

	/**
	 * type of tile: normal, blocked, or door
	 */
	private int type;

	/**
	 * static integers representing the different types of tiles
	 */
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
