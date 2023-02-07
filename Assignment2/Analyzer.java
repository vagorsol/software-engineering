/** 
 * This class contains the methods used for conducting a simple sentiment analysis.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Analyzer {
	
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 */
	public static Set<Sentence> readFile(String filename) {
		Set<Sentence> sents = new HashSet<>(); // TODO: idk which set i want to use :/
		
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
		} catch(FileNotFoundException fnf) {
			System.out.println("Invalid data!");
			return null;
		} catch(IOException e) {
			System.out.println("Invalid data!");
			return null; 
		}
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
		/*
		 * Implement this method in Part 2
		 */
		return null;
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
		/*
		 * Implement this method in Part 3
		 */
		return 0;
	}
	
	public static void main(String[] args) {
		Set<Sentence> sents = readFile("test.txt");
		System.out.println(sents);
		/*
		 * Implement this method in Part 4
		 */
	}

}
