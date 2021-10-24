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
        for (String line : fr.lines()) {
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
        for (LogEntry log : records) {
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

        for (LogEntry log : records) {
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

        for (LogEntry log : records) {
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

        for(LogEntry log : records) {
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

    /**
     * Maps an IP address to the number of times that IP address 
     * appears in records, meaning the number of times this IP address
     * visited the website.
     */
    public HashMap<String, Integer> countVisitesPerIP () {

        HashMap<String, Integer> counts = new HashMap <String, Integer>();

        for (LogEntry log : records) {
            String ip = log.getIpAddress();

            if (!counts.containsKey(ip)) {
                counts.put(ip, 1); 
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    /**
     * Maps an IP address to the number of times that IP address appears 
     * in the web log file. This method returns the maximum number of 
     * visits to this website by a single IP address.
     */
    public int mostNumberVisitsByIP (HashMap<String, Integer> map) {
        
        int max = 0;

        for (int visits : map.values() ) {
            // System.out.println(visits);
            if (visits > max) {
                max = visits;
            }
        }
        return max;
    }

    /**
     * Maps an IP address to the number of times that IP address appears in the 
     * web log file. Returns an ArrayList of Strings of IP addresses that all have
     * the maximum number of visits to this website.
     */
    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> map) {

        int max = mostNumberVisitsByIP(map);
        ArrayList<String> addresses = new ArrayList<String>();

        for (String address : map.keySet()) {
            if (map.get(address) == max) {
                addresses.add(address);
            }
        }
        return addresses;
    }

    /**
     * Returns a HashMap<String,ArrayList<String>> that uses records and maps days 
     * from web logs to an ArrayList of IP addresses that occurred on that day 
     * including repeated IP addresses.
     */
    public HashMap<String, ArrayList<String>> iPsForDays() {

        HashMap<String, ArrayList<String>> iPsForDays = new HashMap<String, ArrayList<String>>();

        for (LogEntry log : records) {
            //MMM DD "Dec 05"
            String date = log.getAccessTime().toString().substring(4, 10);
            
            if (iPsForDays.containsKey(date)) {
                ArrayList<String> addresses = iPsForDays.get(date);
                String adress = log.getIpAddress();
                addresses.add(adress);
                iPsForDays.put(date, addresses);
            } else {
                ArrayList<String> addresses = new ArrayList<String>();
                String adress = log.getIpAddress();
                addresses.add(adress);
                iPsForDays.put(date, addresses);
            }
        }
        return iPsForDays;
    }

    /**
     * Returns the day that has the most IP address visits.
     */
    public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsForDays) {

        String max = null;
        int cant = 0;

        for (String date : iPsForDays.keySet()) {

            ArrayList<String> addresses = iPsForDays.get(date);
            
            if (addresses.size() > cant) {
                cant = addresses.size();
                max = date;
            }
        }
        return max;
    }

    /**
     * Returns an ArrayList<String> of IP addresses that had the most accesses 
     * on the given day MMM DD (Sep 30).
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String date) {
        
        ArrayList<String> addresses = iPsForDays.get(date);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (String adress : addresses) {
            if(map.containsKey(adress)) {
                map.put(adress, map.get(adress) + 1);
            } else {
                map.put(adress, 1);
            }
        }
        return iPsMostVisits(map);
    }

}
