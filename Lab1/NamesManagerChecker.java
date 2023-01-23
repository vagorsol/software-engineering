import java.util.LinkedList;
import java.util.List;

public class NamesManagerChecker {
	

	public static void main(String[] args) {
		
		int exceptions = 0;
		int errors = 0;
		
		Character letter = 'C';
		
		// letter is null
		try {
			List<String> names = new LinkedList<>();
			names.add("Murphy,Chris");
			names.add("Franklin,Benjamin");
			NamesManager.countMatches(null, names); 
			// if this _doesn't_ throw an exception, then it's an error
			errors++;
		}
		catch (IllegalArgumentException e) {
			// this is okay!
		}
		catch (NullPointerException e) {
			exceptions++;
		}
		catch (Exception e) { 
			errors++;
		}
		
		
		// names is null
		try {
			NamesManager.countMatches(letter, null);
			// if this _doesn't_ throw an exception, then it's an error
			errors++;
		}
		catch (IllegalArgumentException e) {
			// this is okay!
		}
		catch (NullPointerException e) {
			exceptions++;
		}
		catch (Exception e) { 
			errors++;
		}
		
		// names contains null String
		try {
			List<String> names = new LinkedList<>();
			names.add("Murphy,Chris");
			names.add(null);
			names.add("Franklin,Benjamin");
			if (NamesManager.countMatches(letter, names) != 1) errors++;
		}
		catch (NullPointerException e) {
			exceptions++;
		}
		catch (Exception e) { 
			exceptions++;
		}
		
		// names contains String that doesn't contain comma
		try {
			List<String> names = new LinkedList<>();
			names.add("Murphy,Chris");
			names.add("Snoopy");
			names.add("Franklin,Benjamin");
			if (NamesManager.countMatches(letter, names) != 1) errors++;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			exceptions++;
		}
		catch (Exception e) {
			exceptions++;
		}
		
		// names contains empty String for firstName
		try {
			List<String> names = new LinkedList<>();
			names.add("Murphy,Chris");
			names.add("Prince,");
			names.add("Franklin,Benjamin");
			if (NamesManager.countMatches(letter, names) != 1) errors++;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			exceptions++;
		}
		catch (Exception e) { 
			exceptions++;
		}

		// names contains empty String for firstName
		try {
			List<String> names = new LinkedList<>();
			names.add("Murphy,Chris");
			names.add("Prince,, ");
			names.add("Franklin,Benjamin");
			if (NamesManager.countMatches(letter, names) != 1) errors++;
		}
		catch (StringIndexOutOfBoundsException e) {
			exceptions++;
		}
		catch (Exception e) { 
			exceptions++;
		}
		
		if (exceptions == 0 && errors == 0) {
			System.out.println("All tests pass! Great job! :-) <3");
		}
		else {
			if (exceptions > 0) {
				System.out.println("There are " + exceptions + " cases in which the method throws a NullPointerException.");
			}
			if (errors > 0) {
				System.out.println("There are " + errors + " cases in which the method doesn't throw a NullPointerException, but returns the wrong value or throws the wrong exception");
			}
		}

		
	}

}
