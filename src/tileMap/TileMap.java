package tileMap;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileMap {

	/**
	 * x coordinate of the tileMap
	 */
	private double x;

	/**
	 * y coordinate of the tileMap
	 */
	private double y;

	/**
	 * 2-D array of integers to represent the map
	 */
	private int[][] map;

	/**
	 * size of a single tile
	 */
	private int tileSize;

	/**
	 * number of rows and columns in the map
	 */
	private int numRows;
	private int numCols;

	/**
	 * widht and height of the tileMap
	 */
	private int width;
	private int height;

	/**
	 * tileSet image containing images of all tiles
	 */
	private BufferedImage tileSet;

	/**
	 * total number of tiles
	 */
	private int numTiles;

	/**
	 * 2-D array of tiles to represent map
	 */
	private Tile[][] tiles;

	/**
	 * number of rows and columns drawn on the screen
	 */
	private int rowsDrawn;
	private int colsDrawn;

	/**
	 * an array of rectangles representing all the wall tiles
	 */
	private Rectangle[] walls;

	/**
	 * an array of rectangles representing all the door tiles
	 */
	private Rectangle[] doors;

	/**
	 * integers representing the number of walls at the loading of the map and
	 * current
	 */
	private int wallCount = 0;
	private int originalWallCount = 0;

	/**
	 * current number of doors
	 */
	private int doorCount = 0;

	/**
	 * create a tileMap with tiles of size ts
	 * 
	 * @param ts
	 *            size of tiles
	 */
	public TileMap(int ts) {
		tileSize = ts;
		rowsDrawn = GamePanel.HEIGHT / tileSize + 2;
		colsDrawn = GamePanel.WIDTH / tileSize + 2;
	}

	/**
	 * load in an image with all tiles and assign certain tiles to different
	 * sections in array
	 * 
	 * @param s
	 *            path to tile image
	 */
	public void loadTiles(String s) {
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTiles = tileSet.getWidth() / tileSize;
			tiles = new Tile[3][numTiles];

			BufferedImage subImage;
			for (int i = 0; i < numTiles; i++) {
				subImage = tileSet.getSubimage(i * tileSize, 0, tileSize, tileSize);
				tiles[0][i] = new Tile(subImage, Tile.NORMAL);
				subImage = tileSet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
				tiles[1][i] = new Tile(subImage, Tile.BLOCKED);
				subImage = tileSet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
				tiles[2][i] = new Tile(subImage, Tile.DOOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * put numbers from map file into 2D array and also form array or all wall
	 * and block tiles
	 * 
	 * @param s
	 *            path to map file
	 */
	public void loadMap(String s) {
		try {
			wallCount = 0;
			doorCount = 0;
			walls = new Rectangle[rowsDrawn * colsDrawn];
			doors = new Rectangle[8];
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			String delim = "\\s+";
			for (int i = 0; i < numRows; i++) {
				String line = br.readLine();
				String[] tokens = line.split(delim);
				for (int j = 0; j < numCols; j++) {
					map[i][j] = Integer.parseInt(tokens[j]);
					if (map[i][j] / numTiles == 1) {
						walls[wallCount] = new Rectangle((int) x + j * tileSize, (int) y + i * tileSize, tileSize,
								tileSize);
						wallCount++;
						originalWallCount++;
					}
					if (map[i][j] / numTiles == 2) {
						doors[doorCount] = new Rectangle((int) x + j * tileSize, (int) y + i * tileSize, tileSize,
								tileSize);
						doorCount++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return y coordinate
	 */
	public int getY() {
		return (int) y;
	}

	/**
	 * @return x coordinate
	 */
	public int getX() {
		return (int) x;
	}

	/**
	 * @return tileSize of the tileMap
	 */
	public int getTileSize() {
		return tileSize;
	}

	/**
	 * @return width of the map
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height of the map
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return rows in the tileMap
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * @return columns in the tileMap
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * @param row
	 * @param col
	 * @return the type of tile at row and col
	 */
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTiles;
		int c = rc % numTiles;
		return tiles[r][c].getType();
	}

	/**
	 * move the tileMap to new cooridnates
	 * 
	 * @param x
	 *            new x coordinate
	 * @param y
	 *            new y coordinate
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return array of all walls
	 */
	public Rectangle[] getWalls() {
		return walls;
	}

	/**
	 * @return number of all walls
	 */
	public int getWallCount() {
		return wallCount;
	}

	/**
	 * @return number of walls when map was loaded
	 */
	public int getOriginalWallCount() {
		return originalWallCount;
	}

	/**
	 * @return array of all doors
	 */
	public Rectangle[] getDoors() {
		return doors;
	}

	/**
	 * @return number of doors
	 */
	public int getDoorCount() {
		return doorCount;
	}

	/**
	 * draw each tile in its specific spot
	 * 
	 * @param g
	 *            graphics object
	 */
	public void draw(Graphics2D g) {
		for (int row = 0; row < rowsDrawn; row++) {
			for (int col = 0; col < colsDrawn; col++) {
				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];

				int r = rc / numTiles;
				int c = rc % numTiles;

				g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);

			}
		}
		// for(int i = 0; i < wallCount; i++) {
		// g.setColor(Color.blue);
		// g.draw(walls[i]);
		// }
	}

	/**
	 * @param i
	 *            new wall count
	 */
	public void setWallCount(int i) {
		wallCount = i;
	}

}
