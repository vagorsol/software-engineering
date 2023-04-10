package states.ui;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import states.common.State;
import states.data.Reader;
import states.processor.JSONStateFinder;
import states.processor.StateFinder;

public class Main1 {
	
	public static void main(String[] args) {
				
		// create a StateFinder that uses JSON
		StateFinder sf1 = new JSONStateFinder("states.json");
		System.out.println(sf1.findState(44.4, -114.8));
		
		// create a StateFinder that uses a different type of Reader implemented here
		StateFinder sf2 = new StateFinder() {
			@Override
			protected Reader getReader() {
				return new Reader() {
					@Override
					public List<State> readStates() {
						List<State> states = new LinkedList<>();
						
						try (Scanner in = new Scanner(new File("states.txt")) ){
							
							while (in.hasNext()) {
								
								String line = in.nextLine();
								
								String[] parts = line.split("\t");
		
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
				};
			}
		};
		System.out.println(sf2.findState(20, -155));

		
	}

}
