package states.ui;
import states.processor.CSVStateFinder;
import states.processor.StateFinder;

public class Main2 {
	
	public static void main(String[] args) {
				
		// create a StateFinder that uses CSV
		StateFinder sf = new CSVStateFinder("states.csv");
		System.out.println(sf.findState(37.6, -78.8));
	}
}
