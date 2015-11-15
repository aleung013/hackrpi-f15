package txt2speech;
import graph.Node;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.util.data.audio.AudioPlayer;


public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ArrayList<String> testString = new ArrayList<String>();
		testString.add("I");
		testString.add("have");
		testString.add("am");
		testString.add("a");
		testString.add("person");
		
		Node I = new Node( "I" );
		Node have = new Node( "have" );
		Node am = new Node( "am" );
		Node a = new Node( "a" );
		Node person = new Node( "person" );
		I.addChild( have );
		I.addChild( am );
		am.addChild( a );
		a.addChild( person );
		System.out.println( testString );
		String result = I.parse( testString );
		System.out.println( result );
		
		MaryInterface marytts = new LocalMaryInterface();
		Set<String> voices = marytts.getAvailableVoices();
		marytts.setVoice(voices.iterator().next());
		// String text = new Scanner(System.in).nextLine();
		String text = result;
		AudioInputStream audio = marytts.generateAudio(text);
		AudioPlayer player = new AudioPlayer(audio);
		player.start();
		player.join();
	}

}