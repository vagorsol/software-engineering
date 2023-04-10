package states.data;
import java.io.FileReader;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import states.common.State;

public class JSONReader implements Reader {
	
	private String filename;
	
	public JSONReader(String filename) {
		this.filename = filename;
	}
	
	public List<State> readStates() {
		if (filename == null) throw new IllegalStateException("filename not specified");
		
		List<State> states = new LinkedList<>();
		
		try {
			Object obj = new JSONParser().parse(new FileReader(filename));
			JSONArray a = (JSONArray) obj;
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iter = a.iterator();
			while (iter.hasNext()) {
				JSONObject o = iter.next();
				
				String name = (String)o.get("name");				
				double latitude = (double)o.get("latitude");
				double longitude = (double)o.get("longitude");
				
				State state = new State(name, latitude, longitude);
				
				states.add(state);
				
			}
	
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IllegalStateException(e);
		}
		
		return states;
	}

}
