package states.processor;
import states.data.JSONReader;
import states.data.Reader;

public class JSONStateFinder extends StateFinder {


	protected String filename;
	
	public JSONStateFinder(String filename) {
		this.filename = filename;
	}
	
	@Override
	public Reader getReader() {
		return new JSONReader(filename);
	}
}
