/** 
 * This class contains the methods used for conducting a simple sentiment analysis.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Analyzer {
	// Memoization: stores scores of previously entered sentences
	private static Map<String, Double> results = new HashMap<>();

	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 */
	public static Set<Sentence> readFile(String filename) {
		Set<Sentence> sents = new HashSet<>();
		
		// open and read file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String line = null;

			// get sentence and score
			while ((line = reader.readLine()) != null) {

				String[] splitLine = line.split(" ");
				int score = 0;
				String text; 
				
				// check if the line is not followed by any text
				if (splitLine.length > 2) {
					// tries to get the score. igonres the line if it can't (whether isn't int or doesn't have a score)
					try {
						score = Integer.parseInt(splitLine[0]);

						// checks to see if the score is within the range [-2,2]. if not, skip
						if (score < -2 || score > 2) {
							continue;
						} else { 
							// get the string
							text = String.join(" ", Arrays.copyOfRange(splitLine, 1,  splitLine.length)); 

							// add Sentence Object to Set
							Sentence sent = new Sentence(score, text);
							sents.add(sent);
						}
					} catch(NumberFormatException e){
						continue; 
					}
				}				
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("Bad input file!");
			return null;
		} catch (IOException e) {
			System.out.println("Invalid data!");
			return null; 
		} catch (NullPointerException n) {
			System.out.println("Bad input file!");
			return null;
		}
		// I have no clue if I'm doing the "throw an IllegalArgumentException" part in the above properly

		return sents;
	}

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		Map<String, Double> ret = new HashMap<>();
		Map<String, Integer> count = new HashMap<>(); // keeps track of how many times a word appears in total (to calculate averages)

		// check if sentences is null or empty
		if (sentences == null || sentences.isEmpty()) {
			return ret;
		}
	
		// get values of words 
		for (Sentence sent : sentences) {
			// get the string + make all of the letters lowercase
			String[] line = sent.getText().toLowerCase().split(" ");
			
			// add all words in sentence
			for (String word : line) {
				// check if word starts with a non letter. proceed if it doesn't
				if (!String.valueOf(word.charAt(0)).matches("\\W")){

					// check if the word is already in the bank - if so, update value
					if (ret.containsKey(word)) {
						double sum = ret.get(word);
						sum += (double) sent.getScore(); 

						// update values
						ret.put(word, sum);
						count.put(word, count.get(word) + 1);
					} else {
						// create word 
						ret.put(word, (double) sent.getScore());
						count.put(word, 1);
					}
				}
			}
		}
		
		// calculate averages
		for (Map.Entry<String, Double> word: ret.entrySet()){
			ret.put(word.getKey(), word.getValue() / (double) count.get(word.getKey()));
		}

		return ret;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		// check if any of the parameters are missing or invalid (null and/or empty)
		if (wordScores == null || wordScores.isEmpty() || sentence == null || sentence.isEmpty()) {
			return 0;
		}

		if (results.containsKey(sentence)){
			return results.get(sentence);
		}

		String[] words = sentence.toLowerCase().split(" "); // assuming there is no punctuation. i guess? 
		
		double score = 0.0;
		int count = 0;
		
		for (String word : words) {
			if(wordScores.containsKey(word)){
				score += wordScores.get(word);
				count++;
			} else if (wordScores.get(word) != null && !String.valueOf(word.charAt(0)).matches("\\W")){
				count++;
			}
		}

		// if none of the values are in the word bank, so that it doesn't return NaN
		if (count == 0) {
			score = 0;
		} else {
			score = score / (double) count;
		}

		// add sentece + calculated score to the memoization
		results.put(sentence, score);
		return score;
	}
	
	public static void main(String[] args) {
		// reads file name as an argument - implement later
		if (args.length < 1) {
			System.out.println("Please enter a file name!");
			return;
		}

		String filename = args[0]; 

		Set<Sentence> sents = readFile(filename);
		if (sents == null) {
			return;
		}

		Map<String, Double> avgs = calculateWordScores(sents);

		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.print("Enter a sentence: ");
				String sentence = scanner.nextLine(); 

				if (sentence.equals("quit")) {
					System.out.println("Goodbye!");
					break; 
				}

				double average = calculateSentenceScore(avgs, sentence);
				System.out.format("The score of your sentence \"%s\" is %.3f\n", sentence, average);

			} while(true); // program will keep going until user exits
		}
	}
}
