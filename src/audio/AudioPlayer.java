package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Creates a audio player class which plays audio clips
 * 
 * @author Jerry Qing
 *
 */
public class AudioPlayer {

	/**
	 * Creates a clip from the Sound package
	 */
	private Clip clip;

	/**
	 * Creates the input streams for the audioplayer
	 * 
	 * @param s
	 *            Location of File
	 */
	public AudioPlayer(String s) {

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream decais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(decais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the audio once
	 */
	public void play() {
		if (clip == null)
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}

	/**
	 * Loops the audio until a call to stop
	 */
	public void loop() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * Pauses the audio
	 */
	public void stop() {
		if (clip.isRunning())
			clip.stop();
	}

	/**
	 * Closes the entire clip
	 */
	public void close() {
		stop();
		clip.close();
	}

}
