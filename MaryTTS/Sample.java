package hackrpi;
import java.util.Scanner;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.util.data.audio.AudioPlayer;


public class Hackrpi {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		MaryInterface marytts = new LocalMaryInterface();
		Set<String> voices = marytts.getAvailableVoices();
		marytts.setVoice(voices.iterator().next());
		String text = new Scanner(System.in).nextLine();
		AudioInputStream audio = marytts.generateAudio(text);
		AudioPlayer player = new AudioPlayer(audio);
		player.start();
		player.join();
	}

}