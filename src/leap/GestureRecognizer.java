package leap;
public class GestureRecognizer {
	
	private boolean rightHand;
	private boolean handIsThere;
	private boolean thumbExt, indexExt, midExt, ringExt, pinkyExt;
	
	public GestureRecognizer(char type) {
		// INIT
		handIsThere = false;
		if(type == 'r')
			rightHand = true;
		else
			rightHand = false;
		thumbExt = false;
		indexExt = false;
		midExt = false;
		ringExt = false;
		pinkyExt = false;
	}
	
	public void setHandIsThere() {
		handIsThere = true;
	}
	
	public void setHandNotThere() {
		handIsThere = false;
	}
	
	// set fingers up/down
	public void setThumbUp() {
		thumbExt = true;
	}
	
	public void setThumbDown() {
		thumbExt = false;
	}
	
	public void setIndexUp() {
		indexExt = true;
	}
	
	public void setIndexDown() {
		indexExt = false;
	}
	
	public void setMidUp() {
		midExt = true;
	}
	
	public void setMidDown() {
		midExt = false;
	}
	
	public void setRingUp() {
		ringExt = true;
	}
	
	public void setRingDown() {
		ringExt = false;
	}
	
	public void setPinkyUp() {
		pinkyExt = true;
	}
	
	public void setPinkyDown() {
		pinkyExt = false;
	}
	
	// Getters
	public boolean thumbIsUp() {
		return thumbExt;
	}
	
	public boolean indexIsUp() {
		return indexExt;
	}
	
	public boolean midIsUp() {
		return midExt;
	}
	
	public boolean ringIsUp() {
		return ringExt;
	}
	
	public boolean pinkyIsUp() {
		return pinkyExt;
	}
	
	public int countUp() {
		int count = 0;
		if(thumbExt)
			++count;
		if(indexExt)
			++count;
		if(midExt)
			++count;
		if(ringExt)
			++count;
		if(pinkyExt)
			++count;
		
		return count;
	}
	
	public char getChar() {
		
		if(countUp() == 5) {
			return '5';
		} else if(countUp() == 4) {
			if(indexExt && midExt && ringExt && pinkyExt && !thumbExt)
				return '4';
		} else if(countUp() == 3) {
			if(indexExt && midExt && !ringExt && !pinkyExt && thumbExt)
				return '3';
			else if(!indexExt && midExt && ringExt && pinkyExt && !thumbExt)
				return '9';
			else if(indexExt && midExt && ringExt && !pinkyExt && !thumbExt)
				return '6';
			else if(indexExt && midExt && !ringExt && pinkyExt && !thumbExt)
				return '7';
			else if(indexExt && !midExt && ringExt && pinkyExt && !thumbExt)
				return '8';
		} else if(countUp() == 2) {
			if(indexExt && midExt && !ringExt && !pinkyExt && !thumbExt)
				return '2';
		} else if(countUp() == 1) {
			if(indexExt && !midExt && !ringExt && !pinkyExt && !thumbExt)
				return '1';
		} else if(countUp() == 0)
			return '0';
		
		return '#';
	}
}
