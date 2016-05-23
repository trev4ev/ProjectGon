package entity;

import gameState.LevelState;
import tileMap.TileMap;

public class AdvancedEnemy extends Enemy {

	public AdvancedEnemy(TileMap tm, LevelState gs, int i) {
		super(tm, gs, i, true);
		health = 3;
	}

}
