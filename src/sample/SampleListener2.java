
//package leap;
import com.leapmotion.leap.*;

public class SampleListener2 extends Listener{

	private GestureRecognizer gr = new GestureRecognizer('r');

	private float thumblastX = 0;
	private float thumblastY = 0;
	private float thumblastZ = 0;
	private float indexlastX = 0;
	private float indexlastY = 0;
	private float indexlastZ = 0;
	private float middlelastX = 0;
	private float middlelastY = 0;
	private float middlelastZ = 0;
	private float ringlastX = 0;
	private float ringlastY = 0;
	private float ringlastZ = 0;
	private float pinkylastX = 0;
	private float pinkylastY = 0;
	private float pinkylastZ = 0;

	public void onConnect(Controller controller){
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
	}

	public float getthumblastX() {return thumblastX;}
	public float getthumblastY() {return thumblastY;}
	public float getthumblastZ() {return thumblastZ;}

	public float getindexlastX() {return indexlastX;}
	public float getindexlastY() {return indexlastY;}
	public float getindexlastZ() {return indexlastZ;}

	public float getmiddlelastX() {return middlelastX;}
	public float getmiddlelastY() {return middlelastY;}
	public float getmiddlelastZ() {return middlelastZ;}

	public float getringlastX() {return ringlastX;}
	public float getringlastY() {return ringlastY;}
	public float getringlastZ() {return ringlastZ;}

	public float getpinkylastX() {return pinkylastX;}
	public float getpinkylastY() {return pinkylastY;}
	public float getpinkylastZ() {return pinkylastZ;}

	public String getgesture() {
		return gr.getChar() + "";
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
				//System.out.println("Radius: " + h.sphereRadius());
				countR = extendedFingerList.count();
				// loop thru finger list
				String output = "";
				for(Finger f: frame.fingers()) {
					Pointable point = f;
					Vector direction = f.direction();

					output += String.format("%.3f",direction.getX()) + "," +
          					 String.format("%.3f",direction.getY()) + "," +
          					 String.format("%.3f",direction.getZ()) + ",";

					if(f.type() == Finger.Type.TYPE_THUMB) {
						if(f.isExtended())
							gr.setThumbUp();
						else
							gr.setThumbDown();

						thumblastX = direction.getX();
						thumblastY = direction.getY();
						thumblastZ = direction.getZ();
					}
					else if(f.type() == Finger.Type.TYPE_INDEX) {
						if(f.isExtended())
							gr.setIndexUp();
						else
							gr.setIndexDown();

						indexlastX = direction.getX();
						indexlastY = direction.getY();
						indexlastZ = direction.getZ();
					}
					else if(f.type() == Finger.Type.TYPE_MIDDLE) {
						if(f.isExtended())
							gr.setMidUp();
						else
							gr.setMidDown();

						middlelastX = direction.getX();
						middlelastY = direction.getY();
						middlelastZ = direction.getZ();
					}
					else if(f.type() == Finger.Type.TYPE_RING) {
						if(f.isExtended())
							gr.setRingUp();
						else
							gr.setRingDown();

						ringlastX = direction.getX();
						ringlastY = direction.getY();
						ringlastZ = direction.getZ();
					}
					else if(f.type() == Finger.Type.TYPE_PINKY) {
						if(f.isExtended())
							gr.setPinkyUp();
						else
							gr.setPinkyDown();

						pinkylastX = direction.getX();
						pinkylastY = direction.getY();
						pinkylastZ = direction.getZ();
					}
				}
				//System.out.println(output);
			}
		}
		//System.out.println("Symbol: " + gr.getChar());
	}
}