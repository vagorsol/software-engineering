import java.util.*;

// rewrite a method using singleton AND iterator design patterns
public class MyStrings implements Iterable<String> {
    protected List<String> strings;
    private static MyStrings instance; 

    private MyStrings() {
        strings = new LinkedList<>(); 
    }

    public static MyStrings getInstance() {
        if (instance == null) instance = new MyStrings();
        return instance; 
    }

    @Override
    public Iterator<String> iterator() {
        return strings.iterator();
    }

    public void add(String s) {
        strings.add(s);
    }

    public static void main(String[] args) {
        MyStrings ms = MyStrings.getInstance();

        ms.add("Hi");
        ms.add("Todd");
        ms.add("Why");

        for(String s : ms) {
            System.out.println(s);
        }
    }
}