import java.io.IOException;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class LeapListener extends Listener {
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}
	
	public void onConnect(Controller controller) {
		System.out.println("Connected");
		
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}
	
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}
	
	public void onFrame(Controller controller) {
		if(controller.isConnected()) //controller is a Controller object
		{
			Frame frame = controller.frame();
			
			System.out.println("Frame ID: " + frame.id()
								+ ", Timestamp :" + frame.timestamp()
								+ ", Hands: " + frame.hands().count()
								+ ", Fingers: " + frame.fingers().count());
		} else {
			System.out.println("Not connected");
		}
	}
}

public class LeapController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeapListener listener = new LeapListener();
		Controller controller = new Controller();
		controller.addListener(listener);
		
		System.out.println("Press enter to quit");
		
		try {
			System.in.read();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		controller.removeListener(listener);
	}

}
