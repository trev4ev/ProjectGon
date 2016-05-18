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
	
	private int rowsDrawn;
	private int colsDrawn;
	
	private Rectangle[] walls;
	private Rectangle[] doors;

	private int wallCount = 0;
	private int doorCount = 0;
	
	public TileMap(int ts) {
		tileSize = ts;
		rowsDrawn = GamePanel.HEIGHT / tileSize;
		colsDrawn = GamePanel.WIDTH / tileSize;
	}
	
	public void loadTiles(String s) {
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTiles = tileSet.getWidth() / tileSize;
			tiles = new Tile[3][numTiles];
			
			BufferedImage subImage;
			for(int i = 0; i < numTiles; i++) {
				subImage = tileSet.getSubimage(i * tileSize, 0, tileSize, tileSize);
				tiles[0][i] = new Tile(subImage, Tile.NORMAL);
				subImage = tileSet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
				tiles[1][i] = new Tile(subImage, Tile.BLOCKED);
				subImage = tileSet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
				tiles[2][i] = new Tile(subImage, Tile.DOOR);
 			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String s) {
		try {
			walls = new Rectangle[rowsDrawn*colsDrawn];
			doors = new Rectangle[8];
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
				String[] tokens = 
						line.split(
								delim);
				for(int j = 0; j < numCols; j++) {
					map[i][j] = Integer.parseInt(tokens[j]);
					if(map[i][j] / numTiles == 1) {
						walls[wallCount] = new Rectangle(j*tileSize,i*tileSize,tileSize,tileSize);
						wallCount++;
					}
					if(map[i][j] / numTiles == 2) {
						doors[doorCount] = new Rectangle(j*tileSize,i*tileSize,tileSize,tileSize);
						doorCount++;
					}
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
	
	public int getNumRows() { 
		return numRows; 
	}
	
	public int getNumCols() { 
		return numCols; 
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
	}
	
	public Rectangle[] getWalls() {
		return walls;
	}
	
	public int getWallCount() {
		return wallCount;
	}
	
	public Rectangle[] getDoors(){
		return doors;
	}
	
	public int getDoorCount() {
		return doorCount;
	}
	
	public void draw(Graphics2D g) {
		for(int row = 0; row < rowsDrawn; row++) {
			for(int col = 0; col < colsDrawn; col++) {
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				
				int r = rc / numTiles;
				int c = rc % numTiles;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
				
			}
		}
	}

}
