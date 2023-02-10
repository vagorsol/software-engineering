import org.json.simple.parser.JSONParser;

public class SimpleClass {
    public static void main(String[] args) {
        // javac --release 8 -cp "json-simple-1.1.1.jar;." SimpleClass.java
        // java -cp "json-simple-1.1.1.jar;." SimpleClass
        new JSONParser(); 
        System.out.println(":p");
    }
}