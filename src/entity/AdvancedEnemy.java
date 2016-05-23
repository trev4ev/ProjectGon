package entity;

import gameState.LevelState;
import tileMap.TileMap;

public class AdvancedEnemy extends Enemy {

	public AdvancedEnemy(TileMap tm, LevelState gs, int i, boolean horizontal) {
		super(tm, gs, i, horizontal);
		speed = 3;
	}

}
