package testing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

import entity.Animation;

public class AnimationTest {
	Animation a = new Animation();

	@Test
	public void testAnimation() {
		assertFalse(a.playedOnce());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetSprites() {
		BufferedImage[] images = new BufferedImage[1];
		a.setSprites(images);
		assertEquals(a.getSprites(), images);
	}

	@Test
	public void testSetDelay() {
		a.setDelay(1);
		assertEquals(a.getDelay(), 1);
	}

	@Test
	public void testSetSprite() {
		a.setSprite(0);
		assertEquals(a.getCurrentSprite(), 0);
	}

}
