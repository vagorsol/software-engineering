package states.processor;

import states.data.Reader;
import states.data.CSVReader;

public class CSVStateFinder extends StateFinder {
    private String filename;

    public CSVStateFinder(String filename) {
        this.filename = filename;
    }

    @Override
    protected Reader getReader() {
        return new CSVReader(filename);
    }
}
