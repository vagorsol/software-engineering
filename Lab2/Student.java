import java.util.LinkedList;
import java.util.List;

public class Student {
	
	public String name;
	public List<Grade> grades;
	
	public Student(String n) {
		name = n;
		grades = new LinkedList<>();
	}
	
	public void add(Grade g) {
		// makes a (NEW) copy of the given date/grade to ensure not copying references
		// (or something like that)
		Date addD = new Date(g.date.month, g.date.day, g.date.year);
		Grade addG = new Grade(addD, g.score);
		grades.add(addG); 
	}
	

}
