package states.processor;
import java.util.List;

import states.common.State;
import states.data.Reader;

public abstract class StateFinder {
	
	protected abstract Reader getReader();
	
	public String findState(double latitude, double longitude) {
		
		List<State> states;
		
		Reader reader = getReader();
	
		if (reader != null) {
			states = reader.readStates();
		}
		else {
			throw new IllegalStateException("Reader not initialized");
		}
		
		double closestDistance = Double.MAX_VALUE;
		String closestState = "none";
		
		for (State state : states) {
			double distance = calculateDistance(state.getLatitude(), state.getLongitude(), latitude, longitude);
			if (distance < closestDistance) {
				closestDistance = distance;
				closestState = state.getName();
			}			
		}
		
		return closestState;
				
	}
	
	public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		
		return Math.sqrt(Math.pow(lat1-lat2,2) + Math.pow(lon1-lon2,2));
		
	}

}
