import java.util.*;

public class Worker {
    public int num = 0;  
    private Map<Integer, Integer> calResult = new TreeMap<>(); 
 	private Calculator calc = new Calculator();
         
 	public int work() {
        if (num < 0) return 0;
        
		if (calResult.containsKey(num)) {
			return calResult.get(num);
		} else {
			int result = calc.calculate(num);
			calResult.put(num, result);
			return result; 
		}                    
 	}
}
