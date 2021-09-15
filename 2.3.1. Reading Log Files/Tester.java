import java.util.*;

public class Tester {

    /**
     * Test Case
     */
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    /**
     * Test Case
     */
    public void testLogAnalyzer() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Calls readFile on the data file short-test_log
        log.readFile("data\\short-test_log");

        // Calls printAll to print all the web logs
        log.printAll();
    }

    public static void main (String[] args ) {
        
        Tester tester = new Tester();

        System.out.println("LogEntry ------------------");
        tester.testLogEntry();
        System.out.println("Analyzer ------------------");
        tester.testLogAnalyzer();
    }
}