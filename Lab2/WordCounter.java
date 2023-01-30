import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordCounter {
	

	public static List<String> readFile(String name) {
		
		// this line determines which type of List is returned
		List<String> words = new LinkedList<>();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(name));
			
			String next = null;
			
			while ((next = br.readLine()) != null) {
				
				String[] tokens = next.split(" ");
				
				for (String token : tokens) {
					// doesn't check if is unique or not! 
					words.add(token.toLowerCase());
				}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { br.close(); }
			catch (Exception e) { }
		}
		
		return words;
	}
	
	/*
	 * Execution lower time ideas:
	 * itt over and make an array list for each with only unique words (?) <- not space efficient but w/e
	 * ????
	 */
	public static List<String> wordsInCommon(List<String> list1, List<String> list2) {
		
		List<String> common = new LinkedList<>();
		
		for (int i = 0; i < list1.size(); i++) {
			// String currI = list1.get(i);
			for (int j = 0; j < list2.size(); j++) {
				// String currJ = list2.get(j);
				if (list1.get(i).equals(list2.get(j))) {
					if (common.contains(list1.get(i)) == false) {
						common.add(list1.get(i));
					}
				}
			}
		}
		
		return common;
		
	}
	
	
	public static void main(String[] args) {
		
		List<String> alice = readFile("alice.txt");
		System.out.println("Read alice.txt");
		
		List<String> wizard = readFile("wizard.txt");
		System.out.println("Read wizard.txt");
		
		long start = System.currentTimeMillis();
		System.out.println("These files have " + wordsInCommon(alice, wizard).size() + " words in common");
		long end = System.currentTimeMillis();
		System.out.println("Took " + (end-start)/1000.0 + "s to run");
		
	}

}
