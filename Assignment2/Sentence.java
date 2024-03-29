/** 
 * This class represents a single sentence from the input file.
 *
 * You may add to this class as needed but PLEASE DO NOT DELETE OR MODIFY THE PROVIDED CODE.
 * 
 * Please notify the Instructor if you feel that it is necessary to 
 * modify any of the code that is provided.
 */


public class Sentence  {
	
	/**
	 * The sentiment score for the sentence. Should be in the range [-2, 2]
	 */
	private int score;
	
	/**
	 * The text contained in the sentence. 
	 */
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj instanceof Sentence == false) return false;
		Sentence other = (Sentence) obj;
		return text.equals(other.text) && score == other.score;
	}

	/* Add methods and instance variables below as needed */
	// I am not sure if this is necessary but I added it just in case
	@Override
	public int hashCode(){
		return this.text.hashCode();
	}
	
}
