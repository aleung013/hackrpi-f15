//package sample;
import com.leapmotion.leap.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JButton;

public class LeapController {

	public static void main(String[] args){
		TTS THEtts = new TTS("hello");

		//interface stuff
		Interface THEinterface = new Interface(1200, 400);

		//JLabel index = THEinterface.createlabel("Index", 20, 20, 50, 50);

		//JButton indexx = THEinterface.createbutton("X", 90, 20, 150, 50);
		//JButton indexy = THEinterface.createbutton("Y", 260, 20, 150, 50);
		//JButton indexz = THEinterface.createbutton("Z", 430, 20, 150, 50);

		JLabel gesturelabel = THEinterface.createlabel("gesture", 20, 20, 150, 50);
		JLabel gesture = THEinterface.createlabel("gesture", 170, 20, 150, 50);

		JLabel sentencelabel = THEinterface.createlabel("Sentence", 20, 90, 150, 50);
		JLabel THEsentence = THEinterface.createlabel("Sentence", 170, 90, 1200, 50);

		JButton speak = THEinterface.createbutton("Speak", 20, 160, 150, 50);
		speak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				THEtts.speak();
			}
		});

		JButton reset = THEinterface.createbutton("Reset", 190, 160, 150, 50);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				THEsentence.setText("");
				THEtts.settext("");
			}
		});

		JLabel note = THEinterface.createlabel("", 20, 230, 600, 50);

		//Create a sample listener and controller.
		SampleListener2 listener = new SampleListener2();
		Controller controller = new Controller();

		//Have the samplelistener receive events from the controller.
		controller.addListener(listener);

		//update values in interface
		int i = 0;
		String lastgesture = "";
		gesturelabel.setText("Gesture: ");
		sentencelabel.setText("Sentence: ");
		THEsentence.setText("");
		int threshold = 0;
		while (i < 1) {
			//indexx.setText("X: " + String.format("%f", listener.getindexlastX()));
			//indexy.setText("Y: " + String.format("%f", listener.getindexlastY()));
			//indexz.setText("Z: " + String.format("%f", listener.getindexlastZ()));

			String THEgesture = listener.getgesture();
			gesture.setText(THEgesture);

			//speech
			if (THEgesture.equals(lastgesture) && !(THEgesture.equals("#"))) {
				threshold++;
				if (threshold == 50000) {
					THEsentence.setText(THEsentence.getText() + THEgesture + " ");

					THEtts.settext(THEsentence.getText());
				}
			}
			else if (!(THEgesture.equals(lastgesture)) && !(THEgesture.equals("#"))) {
				threshold = 0;
			}

			lastgesture = THEgesture;
		}

		System.out.println("Press Enter to quit...");
		try{
			System.in.read();
		}catch(IOException e){
			e.printStackTrace();
		}

		controller.removeListener(listener);
	}

}
