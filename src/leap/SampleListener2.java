import com.leapmotion.leap.*;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;

import org.apache.http.*;
import org.json.*;
import java.util.List;
import java.util.ArrayList;

public class SampleListener extends Listener{
	
    //private GestureRecognizer gr = new GestureRecognizer('r');
	private int count;
	private ArrayList<String> words;
	
    public void onConnect(Controller controller){
    	System.out.println("Connected");
    	controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    	controller.config().setFloat("Gesture.Swipe.MinLength", 0.0f);
		controller.config().setFloat("Gesture.Swipe.MinVelocity", 0f);
		controller.config().save();
		words = new ArrayList<String>();
    }
    public void onFrame(Controller controller){
    	Frame frame = controller.frame();
    	++count;
    	int countL = 0;
		int countR = 0;
		/*if(frame.hands().count() > 0)
		    gr.setHandIsThere();
		else
		    gr.setHandNotThere();*/
			
		for(Hand h: frame.hands()) {
		    FingerList extendedFingerList = h.fingers().extended();
		    if(h.isLeft()) {
		    	countL = extendedFingerList.count();
		    }
		    else if(h.isRight()) {
		    	
				countR = extendedFingerList.count();
				// loop thru finger list
			
				List<Object> datavalues = new ArrayList<Object>();
				datavalues.add("Value");
				for(int i = 0; i < 5; ++i) {
					for(Finger f: frame.fingers()) {
						if(f.type() == Finger.Type.TYPE_THUMB && i == 0) {
						    Vector direction = f.direction();
						    datavalues.add(String.format("%.3f", direction.getX()));
						    datavalues.add(String.format("%.3f", direction.getY()));
						    datavalues.add(String.format("%.3f", direction.getZ()));
						} else if(f.type() == Finger.Type.TYPE_INDEX && i == 1) {
						    Vector direction = f.direction();
						    datavalues.add(String.format("%.3f", direction.getX()));
						    datavalues.add(String.format("%.3f", direction.getY()));
						    datavalues.add(String.format("%.3f", direction.getZ()));
						} else if(f.type() == Finger.Type.TYPE_MIDDLE && i == 2) {
						    Vector direction = f.direction();
						    datavalues.add(String.format("%.3f", direction.getX()));
						    datavalues.add(String.format("%.3f", direction.getY()));
						    datavalues.add(String.format("%.3f", direction.getZ()));
						} else if(f.type() == Finger.Type.TYPE_RING && i == 3) {
						    Vector direction = f.direction();
						    datavalues.add(String.format("%.3f", direction.getX()));
						    datavalues.add(String.format("%.3f", direction.getY()));
						    datavalues.add(String.format("%.3f", direction.getZ()));
						} else if(f.type() == Finger.Type.TYPE_PINKY && i == 4) {
						    Vector direction = f.direction();
						    datavalues.add(String.format("%.3f", direction.getX()));
						    datavalues.add(String.format("%.3f", direction.getY()));
						    datavalues.add(String.format("%.3f", direction.getZ()));
						}
					}
				}
					
				boolean swiped = false;
				for(Gesture g: frame.gestures()) {
					
					switch(g.type()) {
					case TYPE_SWIPE:
						swiped = true;
						SwipeGesture swipe = new SwipeGesture(g);
						
						datavalues.add(String.format("%.3f", swipe.startPosition().getX()));
						datavalues.add(String.format("%.3f", swipe.startPosition().getY()));
						datavalues.add(String.format("%.3f", swipe.startPosition().getZ()));
						datavalues.add(String.format("%.3f", swipe.position().getX()));
						datavalues.add(String.format("%.3f", swipe.position().getY()));
						datavalues.add(String.format("%.3f", swipe.position().getZ()));
						datavalues.add(String.format("%.3f", swipe.speed()));
						datavalues.add(String.format("%.3f", swipe.direction().getX()));
						datavalues.add(String.format("%.3f", swipe.direction().getY()));
						datavalues.add(String.format("%.3f", swipe.direction().getZ()));
						break;
					}
					
					if(swiped)
						break;
				}
				if(!swiped) {
					for(int i = 0; i < 10; ++i)
						datavalues.add(String.format("%.3f", 0.0));
				}

				datavalues.add(String.format("%.3f", h.sphereRadius()));
				
				for(int i = 0; i < 5; ++ i) {
					for(Finger f: frame.fingers()) {
							if(f.type() == Finger.Type.TYPE_THUMB && i == 0) {
								if(f.isExtended())
									datavalues.add(String.format("%d", 1));
								else
									datavalues.add(String.format("%d", 0));
							}
							else if(f.type() == Finger.Type.TYPE_INDEX && i == 1) {
								if(f.isExtended())
									datavalues.add(String.format("%d", 1));
								else
									datavalues.add(String.format("%d", 0));
							}
							else if(f.type() == Finger.Type.TYPE_MIDDLE && i == 2) {
								if(f.isExtended())
									datavalues.add(String.format("%d", 1));
								else
									datavalues.add(String.format("%d", 0));
							}
							else if(f.type() == Finger.Type.TYPE_RING && i == 3) {
								if(f.isExtended())
									datavalues.add(String.format("%d", 1));
								else
									datavalues.add(String.format("%d", 0));
							}
							else if(f.type() == Finger.Type.TYPE_PINKY && i == 4) {
								if(f.isExtended())
									datavalues.add(String.format("%d", 1));
								else
									datavalues.add(String.format("%d", 0));
							}
					}
				}
				
				/*for(int i = 0; i < datavalues.size(); ++i) {
					
					
					System.out.print(datavalues.get(i) + ",");
				}
				System.out.println();*/
				
				if(count % 3 == 0) {
					// poll every 5 frames
					count = 0;
					//Create JSON objects
					String[] columnnames = {"String",
							"Thumbx", "Thumby", "Thumbz",
							"Indexx", "Indexy", "Indexz",
							"Middlex", "Middley", "Middlez",
							"Ringx", "Ringy", "Ringz",
							"Pinkyx", "Pinkyy", "Pinkyz",
							"StartPosx", "StartPosy", "StartPosz",
							"EndPosx", "EndPosy", "EndPosz", "Speed",
							"Dirx", "Diry", "Dirz", "Radius", "B1", "B2", "B3", "B4", "B5"};
					JSONArray colnamearray = new JSONArray(columnnames);
					JSONArray datavaluearray = new JSONArray(datavalues);
					List<JSONArray> n = new ArrayList<JSONArray>();
					n.add(datavaluearray);
					JSONArray datadoublenest = new JSONArray(n);
					JSONObject allinputs = new JSONObject()
						.put("Values", datadoublenest)
						.put("ColumnNames", colnamearray);
					
					String website = "https://ussouthcentral.services.azureml.net/workspaces/0fdb48b0d5aa4e4b92c021a50d58c296/services/dd0d29d4311c46bcbb64f860c30cdde1/execute?api-version=2.0&details=true";
					String apikey = "fy8yESzF9xpk/9znpim/bNeItZtrorH2wsBKU3XD6wrCbUvDHP2znVYZchHLfLeWX9k4b5JF69nSYdsQhfJt8A==";
					try{
						JSONObject inputdata = new JSONObject().put("input1", allinputs);
						
						JSONObject requestobject = new JSONObject();
						requestobject.put("Inputs",inputdata);
						requestobject.put("GlobalParameters",new JSONObject());
						//System.out.println(requestobject.toString());
						
						HttpResponse<JsonNode> jsonresponse = Unirest.post(website)
							.header("Content-Type","application/json")
							.header("Authorization","Bearer " + apikey)
							.body(new JsonNode(requestobject.toString()))
							.asJson();
						
						String result = jsonresponse.getBody().getObject().toString();
						System.out.println(result);
						if(!result.contains("error")) {
							String word = "";
							JSONArray jarr = jsonresponse.getBody().getObject().getJSONObject("Results").getJSONObject("output1").getJSONObject("value").getJSONArray("Values").getJSONArray(0);
							word = jarr.get(jarr.length()-1).toString();
							System.out.println("Interpretation: " + word);
							words.add(word);
						}
						
					}catch(UnirestException e){
						e.printStackTrace();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
		    } else {
		    	
		    }
		}
		
	//System.out.println("Symbol: " + gr.getChar());
    }
}
