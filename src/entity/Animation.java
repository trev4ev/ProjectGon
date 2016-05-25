package entity;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] sprites;
	private int currentSprite;

	private long startTime;
	private long delay;

	private boolean playedOnce;

	public Animation() {
		playedOnce = false;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
		currentSprite = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}

	public void setDelay(long d) {
		delay = d;
	}

	public void setSprite(int s) {
		currentSprite = s;
	}

	public int getCurrentSprite() {
		return currentSprite;
	}

	public void update() {
		if (delay == -1) {
			return;
		}

		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if (elapsed > delay) {
			startTime = System.nanoTime();
			currentSprite++;
			if (currentSprite >= sprites.length) {
				currentSprite = 0;
				playedOnce = true;
			}
		}
	}

	public BufferedImage getImage() {
		return sprites[currentSprite];
	}

	public boolean playedOnce() {
		return playedOnce;
	}
}
