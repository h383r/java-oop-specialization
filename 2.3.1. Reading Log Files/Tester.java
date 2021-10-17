import java.util.*;

public class Tester {

    /**
     * LogEntry Test Case
     */
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    /**
     * Log Analyzer Test Case
     */
    public void testLogAnalyzer() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Calls readFile on the data file short-test_log
        log.readFile("data\\short-test_log");

        // Calls printAll to print all the web logs
        log.printAll();
    }

    /**
     * uniqueIPs Test Case
     */
    public void testUniqueIP() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from the file
        log.readFile("data\\short-test_log");

        // Test the method countUniqueIPs
        int uniqueIPs = log.countUniqueIPs();
        System.out.println(uniqueIPs);
    }

    /**
     * printAllHigherThanNum Test Case
     */
    public void testAllHigherThanNum() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile("data\\short-test_log");

        // Test the method
        log.printAllHigherThanNum(200);
    }

    /**
     * uniqueIPVisitsOnDay Test Case
     */
    public void testUniqueIPVisitsOnDay(String date, String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile(filename);
        
        // Calls method
        ArrayList<String> addresses = log.uniqueIPVisitsOnDay(date);
        System.out.println(addresses.size() + " Visits on " + date);

    }

    /**
     * countUniqueIPsInRange Test Case
     */
    public void testUniqueIPsInRange(int low, int high, String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile(filename);

        // Calls method
        System.out.println(log.countUniqueIPsInRange(low, high));
    }

    /**
     * readFileWithUniqueStatus Test Case
     */
    public void testFileWithUniqueStatus() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Calls readFile on the data file short-test_log
        String filename = "data\\short-test_log";

        // Calls method
        log.readFileWithUniqueStatus(filename);

        // Calls printAll to print all the web logs
        log.printAll();
    }
    
    public static void main (String[] args ) {
        
        Tester tester = new Tester();
        /* 
        */
        System.out.println("------------------ LogEntry");
        tester.testLogEntry();
        
        System.out.println("------------------ Analyzer");
        tester.testLogAnalyzer();
        
        System.out.println("------------------ Unique IPs");
        tester.testUniqueIP();
        
        System.out.println("------------------ Status Code Higher Than 200");
        tester.testAllHigherThanNum();
        
        System.out.println("------------------ IP Visits On Day");
        // Test case 1, should return 2
        tester.testUniqueIPVisitsOnDay("Sep 14", "data\\weblog-short_log");
        // Test case 2, should return 3
        tester.testUniqueIPVisitsOnDay("Sep 30", "data\\weblog-short_log");
        // Quiz
        tester.testUniqueIPVisitsOnDay("Mar 17", "weblog1_log");
        
        System.out.println("------------------ Unique IPs in Status Range");
        // Test case 1, should return 4
        tester.testUniqueIPsInRange(200, 299, "data\\short-test_log");
        // Test case 2, should return 2
        tester.testUniqueIPsInRange(300, 399, "data\\short-test_log");
        // Quiz
        tester.testUniqueIPsInRange(300, 399, "data\\weblog1_log");

        System.out.println("------------------ Read File With Unique Status");
        tester.testFileWithUniqueStatus();
    }
}