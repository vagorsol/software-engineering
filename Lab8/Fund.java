import java.util.Set;
import java.util.TreeSet;

public class Fund {
	
	private final double target;
	protected double balance = 0;
	protected Set<String> donors = new TreeSet<>();
	
	public Fund(double target) { 
		this.target = target;
	}
	 
	public double donate(String user, double donation) {
		
		if (user == null) {
			throw new IllegalArgumentException();
		}

		if (user.isBlank()) {
			throw new IllegalArgumentException();
		}
		
		if (donation < 0) {
			throw new IllegalArgumentException();
		}
		
		if (balance < 0) {
			throw new IllegalStateException();
		}

		// how much is required to meet the target
		double remaining = target - balance; 
		
		if (remaining > 0) {  // if the target has not been reached
			if (donation < remaining) { // if amount would not reach target
				balance += donation;
			}
			else { // if amount would exceed target
				donation = remaining;
				balance += target;
			}
			donors.add(user);
			return donation;
		}
		else { // if target has already been reached
			return 0;
		}
		
	}
}
