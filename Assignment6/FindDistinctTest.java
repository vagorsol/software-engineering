import static org.junit.Assert.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class FindDistinctTest {

    @Test(expected=IllegalArgumentException.class)
    public void testNullList() {
        Person.findDistinct(null);
    }

    @Test
    public void testEmptyList() {
        List<Person> list = new LinkedList<>();
        List<Person> ret = Person.findDistinct(list);

        // should return an empty list if given an empty list
        assertEquals(0, ret.size(), 0.1);
        assertEquals(new LinkedList<>(), ret);
    }

    @Test
    public void testNullEntry() {
        List<Person> list = new LinkedList<>();
        list.add(null);
        List<Person> ret = Person.findDistinct(list);

        // should return an empty list because should ignore null
        assertEquals(0, ret.size(), 0.1);
        assertEquals(new LinkedList<>(), ret);
    }

    @Test
    // well null defense is broken so let me fix that...
    public void testNullName() {
        List<Person> list = new LinkedList<>();
        Person p = new Person(null, "birthplace", new Date(2023, 3,31));
        list.add(p);

        List<Person> ret = Person.findDistinct(list);
        assertEquals(0, ret.size(), 0.1);
        assertEquals(new LinkedList<>(), ret);
    }

    @Test
    public void testDuplicateEntry() {
        List<Person> list = new LinkedList<>(); // list to execute function over
        List<Person> exp = new LinkedList<>(); // "expected list"

        Person p = new Person("Todd Howard", "Florida", new Date(2013, 9, 2));

        list.add(p);
        list.add(p);
        exp.add(p);

        List<Person> ret = Person.findDistinct(list);
        
        // should return a list with only one entry because there is duplicates
        assertEquals(1, ret.size(), 0.1);
        assertEquals(exp, ret);
    }
}