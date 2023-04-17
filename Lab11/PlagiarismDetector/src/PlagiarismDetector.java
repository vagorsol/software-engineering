
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * This class implements a simple plagiarism detection algorithm.
 */
public class PlagiarismDetector {
	
	/*
	 * Returns a Map (sorted by the value of the Integer, in non-ascending order) indicating
	 * the number of matches of phrases of size windowSize or greater between each document in the corpus
	 * 
	 * Note that you may NOT remove this method or change its signature or specification!
	 */
	public static Map<String, Integer> detectPlagiarism(String dirName, int windowSize, int threshold) {
		File dirFile = new File(dirName);
		String[] files = dirFile.list();
		if (files == null) throw new IllegalArgumentException();
		
		Map<String, Integer> numberOfMatches = new HashMap<String, Integer>();
		Map<String, Set<String>> memoize = new HashMap<>();

		/****** ISSUE #1 IS WITHIN THESE NEXT FEW LINES OF CODE! *******/
		// compare each file to all other files
		for (int i = 0; i < files.length; i++) {
			String file1 = files[i];
			// create phrases for file 1
			Set<String> file1Phrases;
			file1Phrases = memoize.get(file1);
			if (file1Phrases == null){
				file1Phrases = createPhrases(dirName + "/" + file1, windowSize);
				if(file1Phrases == null) {
					return null;
				}

				memoize.put(file1, file1Phrases);
			}

			// technically it would say that a given file is plagarizing itself
			// however i am only asked to look at execution time so i will not address that 
			for (int j = 0 + i; j < files.length; j++) { 
				String file2 = files[j];

				// create phrases for file 2
				Set<String> file2Phrases;
				file2Phrases = memoize.get(file2);
				if (file2Phrases == null){
					file2Phrases = createPhrases(dirName + "/" + file2, windowSize);
					if(file2Phrases == null) {
						return null;
					}

					memoize.put(file2, file2Phrases);
				}
				
				// find matching phrases in each Set
				Set<String> matches = findMatches(file1Phrases, file2Phrases);
				
				if (matches == null)
					return null;

				// if the number of matches exceeds the threshold, add it to the Map
				if (matches.size() > threshold) {
					String key = file1 + "-" + file2;
					if (numberOfMatches.containsKey(file2 + "-" + file1) == false && file1.equals(file2) == false) {
						numberOfMatches.put(key,matches.size());
					}
				}				
			}
			
		}		
		
		// sort the results based on the number of matches
		return sortResults(numberOfMatches);
	}
	
	
	/*
	 * This method reads the given file and then converts it into a List of Strings.
	 * It excludes punctuation and converts all words in the file to uppercase.
	 */
	private static List<String> readFile(String filename) {
		if (filename == null) return null;
		
		List<String> words = new ArrayList<String>();
		
		// fixed resource leak
		try (BufferedReader in = new BufferedReader(new FileReader(filename))){
			String line;
			while ((line = in.readLine())  != null) {
				String[] tokens = line.split(" ");
				for (String token : tokens) { 
					// this strips punctuation and converts to uppercase
					words.add(token.replaceAll("[^a-zA-Z]", "").toUpperCase()); 
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return words;
	}

	/*
	 * This method reads a file and converts it into a Set of distinct phrases,
	 * each of size "window". The Strings in each phrase are whitespace-separated.
	 */
	private static Set<String> createPhrases(String filename, int window) {
		/****** ISSUE #2 IS RELATED TO THIS METHOD! *******/
		/** HINT: once we've created phrases for a file, do we need to do it again? **/

		// read the file
		List<String> words = readFile(filename);
		
		if (window < 1) return null;
		
		Set<String> phrases = new HashSet<String>();
		
		// create phrases of size "window" and add to Set
		for (int i = 0; i < words.size() - window + 1; i++) {
			String phrase = "";
			for (int j = 0; j < window; j++) {
				phrase += words.get(i+j) + " ";
			}

			if (phrases.contains(phrase) == false)
				phrases.add(phrase);

		}
		
		return phrases;
	}

	
	
	/*
	 * Returns a Set of Strings that occur in both of the Set parameters.
	 * However, the comparison is case-insensitive.
	 */
	private static Set<String> findMatches(Set<String> myPhrases, Set<String> yourPhrases) {
	
		Set<String> matches = new HashSet<String>();
		
		if (myPhrases != null && yourPhrases != null) {
		
			/****** ISSUE #3 IS WITHIN THESE NEXT FEW LINES OF CODE! *******/
			for (String mine : myPhrases) {
				if (yourPhrases.contains(mine)) {
					matches.add(mine);
				}
			}
		}
		
		return matches;
	}
	
	
	/*
	 * Returns a LinkedHashMap in which the elements of the Map parameter
	 * are sorted according to the value of the Integer, in non-ascending order.
	 */
	private static LinkedHashMap<String, Integer> sortResults(Map<String, Integer> possibleMatches) {
		
		// Because this approach modifies the Map as a side effect of printing 
		// the results, it is necessary to make a copy of the original Map
		Map<String, Integer> copy = new HashMap<String, Integer>();

		for (String key : possibleMatches.keySet()) {
			copy.put(key, possibleMatches.get(key));
		}	
		
		LinkedHashMap<String, Integer> list = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < copy.size(); i++) {
			int maxValue = 0;
			String maxKey = null;
			for (String key : copy.keySet()) {
				if (copy.get(key) > maxValue) {
					maxValue = copy.get(key);
					maxKey = key;
				}
			}
			
			list.put(maxKey, maxValue);
			
			if (copy.containsKey(maxKey)) {
				copy.put(maxKey, -1);
			}
		}
		
		return list;
	}
	

}
