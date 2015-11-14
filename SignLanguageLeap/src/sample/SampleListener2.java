
import com.leapmotion.leap.*;

public class SampleListener2 extends Listener{
	
	private GestureRecognizer gr = new GestureRecognizer('r');
	
	public void onConnect(Controller controller){
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	}
	public void onFrame(Controller controller){
		Frame frame = controller.frame();

		int countL = 0;
		int countR = 0;
		if(frame.hands().count() > 0)
			gr.setHandIsThere();
		else
			gr.setHandNotThere();
		
		for(Hand h: frame.hands()) {
			FingerList extendedFingerList = h.fingers().extended();
			if(h.isLeft()) {
				countL = extendedFingerList.count();
			}
			else if(h.isRight()) {
				System.out.println("Radius: " + h.sphereRadius());
				countR = extendedFingerList.count();
				// loop thru finger list
				for(Finger f: frame.fingers()) {
					if(f.type() == Finger.Type.TYPE_THUMB) {
						if(f.isExtended())
							gr.setThumbUp();
						else
							gr.setThumbDown();
					}
					else if(f.type() == Finger.Type.TYPE_INDEX) {
						if(f.isExtended())
							gr.setIndexUp();
						else
							gr.setIndexDown();
					}
					else if(f.type() == Finger.Type.TYPE_MIDDLE) {
						if(f.isExtended())
							gr.setMidUp();
						else
							gr.setMidDown();
					}
					else if(f.type() == Finger.Type.TYPE_RING) {
						if(f.isExtended())
							gr.setRingUp();
						else
							gr.setRingDown();
					}
					else if(f.type() == Finger.Type.TYPE_PINKY) {
						if(f.isExtended())
							gr.setPinkyUp();
						else
							gr.setPinkyDown();
					}
				}
			}
		}
		//System.out.println("Symbol: " + gr.getChar());
	}
}
