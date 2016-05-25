package tileMap;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private int type;

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	public static final int DOOR = 2;

	public Tile(BufferedImage image, int type) {
		this.type = type;
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

}
