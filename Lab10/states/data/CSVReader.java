package states.data;
import java.io.File;
import java.util.*;

import states.common.State;

public class CSVReader implements Reader {
	
	private String filename;
	
	public CSVReader(String filename) {
		this.filename = filename;
	}
	
	public List<State> readStates() {
		if (filename == null) throw new IllegalStateException("filename not specified");
		
		List<State> states = new LinkedList<>();
		
		try (Scanner in = new Scanner(new File(filename)) ){
			
			while (in.hasNext()) {
				
				String line = in.nextLine();
				
				String[] parts = line.split(",");

				String name = parts[0];
				double latitude = Double.parseDouble(parts[1]);
				double longitude = Double.parseDouble(parts[2]);
				
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
