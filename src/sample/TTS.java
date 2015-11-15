import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTS {

	private static final String VOICENAME_kevin = "kevin";
	private String text; // string to speech

	public TTS(String text) {
		this.text = text;
	}

	public void settext(String text) {
		this.text = text;
	}

	public void speak() {
		Voice voice;
		VoiceManager voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}
}