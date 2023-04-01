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
}
