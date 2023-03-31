import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CalculateWordScoresTest {
    private Set<Sentence> sents;
    
    @Before
    public void setUp() {
        sents = new HashSet<>();
    }

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
        sents.add(new Sentence(2, "it It"));

        Map<String, Double> ret = Analyzer.calculateWordScores(sents);

        assertEquals(1, ret.size(), 0.1);
        assertEquals(2, ret.get("it"), 0.1);
    }
}
