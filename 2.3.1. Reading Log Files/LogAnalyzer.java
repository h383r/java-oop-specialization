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
     * Prints all records in the Log
     */
    public void printAll() {

        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    /**
     * Return an integer representing the number of unique IP addresses.
     */
    public int countUniqueIPs () {

        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry log: records) {
            String ip = log.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();   
    }

    /**
     * Examine all the web log entries in records
     * Print those LogEntrys that have a status code greater than num
     */
    public void printAllHigherThanNum (int num) {

        for (LogEntry log: records) {
            if (log.getStatusCode() > num) {
                System.out.println(log);
            }
        }
    }
    
    /**
     * Accesses the web logs in records and returns an ArrayList
     * of Strings of unique IP addresses that had access on the
     * given day
     * @param someday Date in format “MMM DD” (
     * MMM is the first three characters of the month name.
     * DD is the day in two digits
     * Examples are “Dec 05” and “Apr 22”.)
     */
    public ArrayList<String> uniqueIPVisitsOnDay (String someday) {

        ArrayList<String> addresses = new ArrayList<String>();

        for (LogEntry log: records) {
            String date = log.getAccessTime().toString();
            String address = log.getIpAddress();
            if (date.contains(someday) && !addresses.contains(address)) {
                addresses.add(address);
            }
        }
        return addresses;
    }

    /**
     * Returns the number of unique IP addresses in records that have a status
     * code in the range from low to high, inclusive.
     */
    public int countUniqueIPsInRange (int low, int high) {
        
        ArrayList<String> addresses = new ArrayList<String>();

        for(LogEntry log: records) {
            String address = log.getIpAddress();
            int status = log.getStatusCode();
            if (!addresses.contains(address) && status >= low && status <= high) {
                addresses.add(address);
            }
        }
        return addresses.size();
    }

    /**
     * Read in web log entries, but only put a LogEntry in
     * records if it hasn’t seen the LogEntry's status
     * code before. Thus, the entries in records would 
     * have unique status code
     */
    public void readFileWithUniqueStatus (String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry le = WebLogParser.parseEntry(line);
            if (! records.contains(le)) {
                records.add(le);
            }
        }

        /*
        The method does not work because a status code is 
        compared to a LogEntry. Instead, a for loop is 
        needed to iterate over all the status codes of the 
        LogEntrys in records to check to see if the 
        statusCode is already in there. After looping, if 
        it is not in there, then the LogEntry can be added 
        to records. 
        */
    }

}
