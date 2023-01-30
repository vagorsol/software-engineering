
public class StudentTest {
	

	public static void main(String[] args) {
		/*
		 * This part of the code creates Date and Grade objects and uses them to create a Student 
		 */

		// create a Date object
		Date today = new Date(1, 31, 2022);
		
		// use it to create a Grade object
		Grade grade = new Grade(today, 4);
		
		// create Student s1
		Student s1 = new Student("s1");
		
		// add the Grade to Student s1
		s1.add(grade);
		
		// grade.score should be 4 and grade.date.day should be 31
		if (s1.grades.get(0).score != 4.0) {
			System.out.println("ERROR! Student1 grade.score should be 4.0, but it's " + s1.grades.get(0).score); 
			System.exit(0);			
		}
		if (s1.grades.get(0).date.day != 31) {
			System.out.println("ERROR! Student1 grade.date.day should be 31, but it's " + s1.grades.get(0).date.day);
			System.exit(0);
		}
		
		/*
		 * This part of the code modifies the Date and Grade objects and uses them to create another Student
		 */

		// modify the Grade's score
		grade.score = 3;

		// modify the Date's day
		today.day = 30;
		
		// create Student s2
		Student s2 = new Student("s2");
		
		// add the Grade to Student s2
		s2.add(grade);
		System.out.println("INFO: updated Student2");
		
		/*
		 * Now we'll make sure we haven't inadvertently changed anything in Student s1 
		 */
		boolean error = false;
		if (s1.grades.get(0).score != 4.0) {
			System.out.println("ERROR! Student1 grade.score should still be 4.0, but now it's " + s1.grades.get(0).score);
			error = true;
		}
		if (s1.grades.get(0).date.day != 31) {
			System.out.println("ERROR! Student1 grade.date.day should still be 31, but now it's " + s1.grades.get(0).date.day); 
			error = true;
		}

		if (!error) {
			System.out.println("ALL TESTS PASSED! Great job!");
		}
		
	}

}
