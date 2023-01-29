import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Person {
	
	private String name;
	private String birthplace;
	private Date birthdate;
	
	public Person(String name, String birthplace, Date birthdate) {
		this.name = name;
		this.birthplace = birthplace;
		this.birthdate = birthdate;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBirthplace() {
		return birthplace;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}

	/*
	 * Returns a List consisting only of non-null distinct Person objects from the input
	 * Here, equal means same name/birthplace/birthdate 
	 */
	public static List<Person> findDistinct(List<Person> persons) {
		if (persons == null) {
			throw new IllegalArgumentException();
		}

		List<Person> ret = new LinkedList<>(); 

		for (Person person : persons) {
			if (person != null) {
				// if the list is empty, just add the person 
				// NOTE: I'm not actually sure if this is necessary but better to be safe
				if (ret.isEmpty()){
					ret.add(person);
				} else{
					for (Person guy : ret) {
						// defend against a null name/birthplace/birthdate
						if (person.getName() != null && person.getBirthplace() != null && person.getBirthdate() != null){
							if (!person.getName().equals(guy.getName()) && !person.getBirthplace().equals(guy.getBirthplace()) && !person.getBirthdate().equals(guy.getBirthdate())) {
								ret.add(person);
							}	
						}
					}
				}
			}
		}
		System.out.println(ret);
		return ret;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		List<Person> list = new LinkedList<>();
		Person guyA = new Person("Todd Howard", "Florida", new Date(2013, 9, 2));
		Person guyB = new Person("John Madden", "Ohio", new Date(2017, 8, 5));

		list.add(guyA);
		list.add(guyB);

		findDistinct(list);
	}
}
