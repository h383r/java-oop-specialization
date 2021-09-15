import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    
    private ArrayList<LogEntry> records;
    
    /**
     * Constructor, initialize records to an empty ArrayList
     */
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
    
    /**
     * Iterate over all the lines in the file.
     * Create a LogEntry and store it in the records ArrayList
     * @param filename
     */
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
    
    /**
     * Prints all records
     */
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
