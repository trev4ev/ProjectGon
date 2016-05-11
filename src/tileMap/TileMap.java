package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	
	public void loadMap(String s) {
		try {
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			String delim = "\\s+";
			for(int i = 0; i < numRows; i++) {
				String line = br.readLine();
				String[] tokens = line.split(delim);
				for(int j = 0; j < numCols; j++) {
					map[i][j] = Integer.parseInt(tokens[j]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getY() {
		return (int)y;
	}

	public int getX() {
		return (int)x;
	}

	public int getTileSize() {
		return tileSize;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTiles;
		int c = rc % numTiles;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
	}
	
	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g) {
		for(int row = rowOffset; row < rowOffset + rowsDrawn; row++) {
			if(row >= numRows) break;
			for(int col = colOffset; col < colOffset + colsDrawn; col++) {				
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTiles;
				int c = rc % numTiles;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
				
			}
		}
	}

}
