import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

// NOTE: reading score should be handled in reading
public class CalculateWordScoresTest {

    @Test
    public void testNullSents() {
        Map<String, Double> ret = Analyzer.calculateWordScores(null);

        assertEquals(Collections.emptyMap(), ret);
    }

    @Test
    public void testEmptySents() {
        Map<String, Double> ret = Analyzer.calculateWordScores(Collections.emptySet()); 

        assertEquals(Collections.emptyMap(), ret);
    }

    @Test
    public void testCaseConversion() {
        Map<String, Double> ret = Analyzer.calculateWordScores(Set.of(new Sentence(2, "it It")));

        assertEquals(1, ret.size(), 0.1);
        assertEquals(2, ret.get("it"), 0.1);
    }

    @Test
    public void testIgnoreNonLetterStart() {
        Map<String, Double> ret = Analyzer.calculateWordScores(Set.of(new Sentence(2, "it 's'")));

        assertEquals(1, ret.size(), 0.1);
        assertEquals(2, ret.get("it"), 0.1);
    }

    @Test
    public void testNullString() {
        Map<String, Double> ret = Analyzer.calculateWordScores(Set.of(new Sentence(2, null)));

        assertEquals(0, ret.size(), 0.1);
        assertEquals(Collections.emptyMap(), ret);
    }
}
