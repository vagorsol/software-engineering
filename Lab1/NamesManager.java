import java.util.LinkedList;
import java.util.List;

public class NamesManager {
	
	public static int countMatches (Character letter, List<String> names) {
		if (Character.isLetter(letter) == false) {
			return 0;
		}
		int count = 0;
		for (String name : names) {
			String firstName = name.split(",")[1];
			if (firstName.charAt(0) == letter)
				count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		List<String> list = new LinkedList<>();
		list.add("Hopper,Grace");
		list.add("Lovelace,Ada");
		list.add("Borg,Anita");

		Character c = 'A';
		
		int matches = countMatches(c, list);
		System.out.println("Number of matches: " + matches);
		
	}
	
	


}
