import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CalculateSentenceScoreTest {

    @Test
    public void testNullWordScores() {
        double score = Analyzer.calculateSentenceScore(null, "ten red dogs");

        assertEquals(0, score, 0.1);
    }

    @Test
    public void testNullSentence() {
        double score = Analyzer.calculateSentenceScore(Map.of("it", 2.0), null);

        assertEquals(0, score, 0.1);
    }

    @Test
    public void testEmptyWordScores() {
        double score = Analyzer.calculateSentenceScore(Map.of(), "ten red dogs");

        assertEquals(0, score, 0.1);
    }

    @Test
    public void testEmptySentence() {
        double score = Analyzer.calculateSentenceScore(Map.of("it", 2.0), "");

        assertEquals(0, score, 0.1);
    }

    @Test
    public void testNonLetterWordStart() {
        double score = Analyzer.calculateSentenceScore(Map.of("smart", 2.0), "?smart");

        assertEquals(0, score, 0.1);
    }

    @Test
    public void testLetterNotInMap() {
        double score = Analyzer.calculateSentenceScore(Map.of("smart", 2.0), "cat");

        assertEquals(0, score, 0.1);
    }

    @Test 
    public void testWordUppercase() {
        double score = Analyzer.calculateSentenceScore(Map.of("smart", 2.0), "Smart");

        assertEquals(2, score, 0.1);
    }

}
