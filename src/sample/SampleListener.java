import com.leapmotion.leap.*;

import java.util.List;
import java.util.ArrayList;

public class SampleListener extends Listener{

    private GestureRecognizer gr = new GestureRecognizer('r');
    private int count;

    public String getgesture() {
		return gr.getChar() + "";
	}

    public void onConnect(Controller controller){
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.config().setFloat("Gesture.Swipe.MinLength", 0.0f);
        controller.config().setFloat("Gesture.Swipe.MinVelocity", 0f);
        controller.config().save();
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

                List<Object> dataValue = new ArrayList<Object>();
                dataValue.add("#c#");
                for(int i = 0; i < 5; ++i) {
                    for(Finger f: frame.fingers()) {
                        if(f.type() == Finger.Type.TYPE_THUMB && i == 0) {
                            Vector direction = f.direction();
                            dataValue.add(String.format("%.3f", direction.getX()));
                            dataValue.add(String.format("%.3f", direction.getY()));
                            dataValue.add(String.format("%.3f", direction.getZ()));
                        } else if(f.type() == Finger.Type.TYPE_INDEX && i == 1) {
                            Vector direction = f.direction();
                            dataValue.add(String.format("%.3f", direction.getX()));
                            dataValue.add(String.format("%.3f", direction.getY()));
                            dataValue.add(String.format("%.3f", direction.getZ()));
                        } else if(f.type() == Finger.Type.TYPE_MIDDLE && i == 2) {
                            Vector direction = f.direction();
                            dataValue.add(String.format("%.3f", direction.getX()));
                            dataValue.add(String.format("%.3f", direction.getY()));
                            dataValue.add(String.format("%.3f", direction.getZ()));
                        } else if(f.type() == Finger.Type.TYPE_RING && i == 3) {
                            Vector direction = f.direction();
                            dataValue.add(String.format("%.3f", direction.getX()));
                            dataValue.add(String.format("%.3f", direction.getY()));
                            dataValue.add(String.format("%.3f", direction.getZ()));
                        } else if(f.type() == Finger.Type.TYPE_PINKY && i == 4) {
                            Vector direction = f.direction();
                            dataValue.add(String.format("%.3f", direction.getX()));
                            dataValue.add(String.format("%.3f", direction.getY()));
                            dataValue.add(String.format("%.3f", direction.getZ()));
                        }
                    }
                }

                boolean swiped = false;
                for(Gesture g: frame.gestures()) {

                    switch(g.type()) {
                    case TYPE_SWIPE:
                        swiped = true;
                        SwipeGesture swipe = new SwipeGesture(g);

                        dataValue.add(String.format("%.3f", swipe.startPosition().getX()));
                        dataValue.add(String.format("%.3f", swipe.startPosition().getY()));
                        dataValue.add(String.format("%.3f", swipe.startPosition().getZ()));
                        dataValue.add(String.format("%.3f", swipe.position().getX()));
                        dataValue.add(String.format("%.3f", swipe.position().getY()));
                        dataValue.add(String.format("%.3f", swipe.position().getZ()));
                        dataValue.add(String.format("%.3f", swipe.speed()));
                        dataValue.add(String.format("%.3f", swipe.direction().getX()));
                        dataValue.add(String.format("%.3f", swipe.direction().getY()));
                        dataValue.add(String.format("%.3f", swipe.direction().getZ()));
                        break;
                    }

                    if(swiped)
                        break;
                }
                if(!swiped) {
                    for(int i = 0; i < 10; ++i)
                        dataValue.add(String.format("%.3f", 0.0));
                }

                dataValue.add(String.format("%.3f", h.sphereRadius()));

                for(int i = 0; i < 5; ++ i) {
                    for(Finger f: frame.fingers()) {
                            if(f.type() == Finger.Type.TYPE_THUMB && i == 0) {
                                if(f.isExtended())
                                    dataValue.add(String.format("%d", 1));
                                else
                                    dataValue.add(String.format("%d", 0));
                            }
                            else if(f.type() == Finger.Type.TYPE_INDEX && i == 1) {
                                if(f.isExtended())
                                    dataValue.add(String.format("%d", 1));
                                else
                                    dataValue.add(String.format("%d", 0));
                            }
                            else if(f.type() == Finger.Type.TYPE_MIDDLE && i == 2) {
                                if(f.isExtended())
                                    dataValue.add(String.format("%d", 1));
                                else
                                    dataValue.add(String.format("%d", 0));
                            }
                            else if(f.type() == Finger.Type.TYPE_RING && i == 3) {
                                if(f.isExtended())
                                    dataValue.add(String.format("%d", 1));
                                else
                                    dataValue.add(String.format("%d", 0));
                            }
                            else if(f.type() == Finger.Type.TYPE_PINKY && i == 4) {
                                if(f.isExtended())
                                    dataValue.add(String.format("%d", 1));
                                else
                                    dataValue.add(String.format("%d", 0));
                            }
                    }
                }

                for(int i = 0; i < dataValue.size(); ++i) {
                    System.out.print(dataValue.get(i) + ",");
                }
                System.out.println();

                /*if(count % 3 == 0) {
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
                            "Dirx", "Diry", "Dirz", "Radius"};
                    JSONArray colnamearray = new JSONArray(columnnames);
                    JSONArray data#c#array = new JSONArray(dataValue);
                    List<JSONArray> n = new ArrayList<JSONArray>();
                    n.add(data#c#array);
                    JSONArray datadoublenest = new JSONArray(n);
                    JSONObject allinputs = new JSONObject()
                        .put("Value", datadoublenest)
                        .put("ColumnNames", colnamearray);

                    String website = "https://ussouthcentral.services.azureml.net/workspaces/0fdb48b0d5aa4e4b92c021a50d58c296/services/ad696dda2ed44bc8b4a306cf4a349280/execute?api-version=2.0&details=true";
                    String apikey = "NF/CHXwBi/DihCVARg2hUYw+Qu8lXEsnkpi/97SEKLjfQIzJO7aKS4bqAGS9bzyPI7l8bInfQ4RCyrvNdAlOmQ==";
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
                        //System.out.println(result);
                        if(!result.contains("error")) {
                            String word = "";
                            JSONArray jarr = jsonresponse.getBody().getObject().getJSONObject("Results").getJSONObject("output1").getJSONObject("#c#").getJSONArray("Value").getJSONArray(0);
                            word = jarr.get(jarr.length()-1).toString();
                            System.out.println("Interpretation: " + word);
                        }

                    }catch(UnirestException e){
                        e.printStackTrace();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }*/

            }
        }

    //System.out.println("Symbol: " + gr.getChar());
    }
}