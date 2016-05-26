package entity;

import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] sprites;
	private int currentSprite;

	private long startTime;
	private long delay;

	private boolean playedOnce;

	/**
	 * create an Animation object
	 */
	public Animation() {
		playedOnce = false;
	}

	/**
	 * sets this array of images to be the sprites which will be cycled through
	 * 
	 * @param sprites
	 *            an array of BufferedImages to cycle through
	 */
	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
		currentSprite = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}

	/**
	 * sets the delay in between each sprite to this number in milliseconds
	 * 
	 * @param d
	 *            length of delay
	 */
	public void setDelay(long d) {
		delay = d;
	}

	/**
	 * sets the animation to this sprite in the cycle
	 * 
	 * @param s
	 *            sprite to be set to
	 */
	public void setSprite(int s) {
		currentSprite = s;
	}

	/**
	 * @return the current sprite
	 */
	public int getCurrentSprite() {
		return currentSprite;
	}

	/**
	 * if the proper amount of time has passed, set the currentSprite to the
	 * next sprite in the cycle
	 */
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

	/**
	 * @return the image which the sprite is currently on
	 */
	public BufferedImage getImage() {
		return sprites[currentSprite];
	}

	/**
	 * @return true if the animation has cycled through once
	 */
	public boolean playedOnce() {
		return playedOnce;
	}
}
