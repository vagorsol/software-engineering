import java.util.*;

// use lazy initialization, lazy evaluation, + memoization
public class Worker {
    public int num = 0;  
 	private Calculator calc = new Calculator();
         
 	public int work() {
              
      	int result = calc.calculate(num); 
              
      	if (num < 0) return 0;
      	else return result;
                    
 	}

}
