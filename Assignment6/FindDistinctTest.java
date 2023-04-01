import static org.junit.Assert.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

// NOTE: I am assuming blank fields, while in practice not meaningful, count as "valid values"
@SuppressWarnings("deprecation")
public class FindDistinctTest {

    @Test(expected=IllegalArgumentException.class)
    public void testNullList() {
        Person.findDistinct(null);
    }

    @Test
    public void testEmptyList() {
        List<Person> ret = Person.findDistinct(List.of());

        // should return an empty list if given an empty list
        assertEquals(0, ret.size(), 0.1);
        assertEquals(List.of(), ret);
    }

    @Test
    public void testNullEntry() {
        Person p = null;
        List<Person> list = new LinkedList<>();
        list.add(p);
        
        List<Person> ret = Person.findDistinct(list);

        // should return an empty list because should ignore null
        assertEquals(0, ret.size(), 0.1);
        assertEquals(List.of(), ret);
    }

    @Test
    public void testNullName() {
        Person p = new Person(null, "birthplace", new Date(2023, 3,31));
        List<Person> ret = Person.findDistinct(List.of(p));

        assertEquals(0, ret.size(), 0.1);
        assertEquals(List.of(), ret);
    }

    @Test
    public void testNullBirthplace() {
        Person p = new Person("name", null, new Date(2023, 3,31));
        List<Person> ret = Person.findDistinct(List.of(p));

        assertEquals(0, ret.size(), 0.1);
        assertEquals(List.of(), ret);
    }

    @Test
    public void testNullDate() {
        Person p = new Person("nam", "birthplace", null);

        List<Person> ret = Person.findDistinct(List.of(p));

        assertEquals(0, ret.size(), 0.1);
        assertEquals(List.of(), ret);
    }
    
    @Test
    public void testDuplicateEntry() {
        Person p = new Person("Todd Howard", "Florida", new Date(2013, 9, 2));
        List<Person> ret = Person.findDistinct(List.of(p, p));
        
        // should return a list with only one entry because there is duplicates
        assertEquals(1, ret.size(), 0.1);
        assertEquals(List.of(p), ret);
    }
}