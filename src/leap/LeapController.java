package leap;
import com.leapmotion.leap.*;
import java.io.*;
import java.util.*;

public class LeapController {
	public static void main(String[] args){
		//Create a sample listener and controller
		SampleListener2 listener = new SampleListener2();
		Controller controller = new Controller();
		
		//Have the samplelistener receive events from the controller
		controller.addListener(listener);
		
		System.out.println("Press Enter to quit...");
		try{
			System.in.read();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		controller.removeListener(listener);
	}
}
