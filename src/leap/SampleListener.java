package leapmotion;
import com.leapmotion.leap.*;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.apache.http.*;
import org.json.*;
import java.util.List;
import java.util.ArrayList;

public class SampleListener extends Listener{
	
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
		    	
				countR = extendedFingerList.count();
				// loop thru finger list
			
				List<Object> datavalues = new ArrayList<Object>();
				datavalues.add(null);
				for(Finger f: frame.fingers()) {
				    Pointable point = f;
				    Vector direction = f.direction();
				    datavalues.add(String.format("%.3f", direction.getX()));
				    datavalues.add(String.format("%.3f", direction.getY()));
				    datavalues.add(String.format("%.3f", direction.getZ()));
				}
				
				//Create JSON objects
				String[] columnnames = {"String",
						"Thumbx", "Thumby", "Thumbz",
						"Indexx", "Indexy", "Indexz",
						"Middlex", "Middley", "Middlez",
						"Ringx", "Ringy", "Ringz",
						"Pinkyx", "Pinkyy", "Pinkyz"};
				JSONArray colnamearray = new JSONArray(columnnames);
				JSONArray datavaluearray = new JSONArray(datavalues);
				JSONObject allinputs = new JSONObject()
					.put("ColumnNames", colnamearray)
					.put("Values", datavaluearray);
				String bob = null;
				String website = "https://ussouthcentral.services.azureml.net/workspaces/0fdb48b0d5aa4e4b92c021a50d58c296/services/d4cd3ed4a11347df8a64dc3e4318c5fa/execute?api-version=2.0&details=true";
				String apikey = "J5ItUA+5ec43KE/NKXTxXsrcFx5NV3HkCctV1gXyR5RZ0fCa0casDEtBpHAfapC2o/PbDqFe6yTkiGBt4LGbLA==";
				try{
					JSONObject inputdata = new JSONObject("");
					System.out.println("Input data: " + inputdata);
					HttpResponse<JsonNode> jsonResponse = Unirest.post(website)
						.header("Content-Type","application/json")
						.header("Authorization",("Bearer " + apikey))
						.field("Inputs",allinputs)
						.field("GlobalParameters",new JSONObject())
						.asJson();
				}catch(UnirestException e){
				}catch(Exception e){
					
				}
				
				
		    }
		}
		
	//System.out.println("Symbol: " + gr.getChar());
    }
}
