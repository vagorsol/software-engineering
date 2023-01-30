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
		 grades.add(g); 		
	}
	

}
