package TileMap;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileMap {
	
	private double x;
	private double y;
	
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	private BufferedImage tileSet;
	private int numTiles;
	private Tile[][] tiles;
	
	private int rowOffset;
	private int colOffset;
	private int rowsDrawn;
	private int colsDrawn;
	
	public TileMap(int ts) {
		tileSize = ts;
		rowsDrawn = GamePanel.HEIGHT / tileSize + 2;
		colsDrawn = GamePanel.WIDTH / tileSize + 2;
	}
	
	public void loadTiles(String s) {
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTiles = tileSet.getWidth() / tileSize;
			tiles = new Tile[2][numTiles];
			
			BufferedImage subImage;
			for(int i = 0; i < numTiles; i++) {
				subImage = tileSet.getSubimage(i * tileSize, 0, tileSize, tileSize);
				tiles[0][i] = new Tile(subImage, Tile.NORMAL);
				subImage = tileSet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
				tiles[1][i] = new Tile(subImage, Tile.BLOCKED);
 			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		
	}
}
