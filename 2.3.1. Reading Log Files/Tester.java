import java.util.*;

public class Tester {

    public void test_logEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void test_logAnalyzer() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Calls readFile on the data file short-test_log
        log.readFile("data\\short-test_log");

        // Calls printAll to print all the web logs
        log.printAll();
    }

    public void test_uniqueIP(String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from the file
        log.readFile(filename);

        // Test the method countUniqueIPs
        int uniqueIPs = log.countUniqueIPs();
        System.out.println(uniqueIPs);
    }

    public void test_allHigherThanNum() {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile("data\\short-test_log");

        // Test the method
        log.printAllHigherThanNum(200);
    }

    public void test_uniqueIPVisitsOnDay(String date, String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile(filename);
        
        // Calls method
        ArrayList<String> addresses = log.uniqueIPVisitsOnDay(date);
        System.out.println(addresses.size() + " Visits on " + date);

    }

    public void test_uniqueIPsInRange(int low, int high, String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Read from file
        log.readFile(filename);

        // Calls method
        System.out.println(log.countUniqueIPsInRange(low, high));
    }

    public void test_fileWithUniqueStatus(String filename) {

        // Creates a LogAnalyzer object
        LogAnalyzer log = new LogAnalyzer();

        // Calls method
        log.readFileWithUniqueStatus(filename);

        // Calls printAll to print all the web logs
        log.printAll();
    }

    public void test_countVisitsPerIP(String filename) {

        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, Integer> counts = log.countVisitesPerIP();
        System.out.println(counts);
    }

    public void test_mostNumberVisitsByIP(String filename) {

        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, Integer> counts = log.countVisitesPerIP();
        int max = log.mostNumberVisitsByIP(counts);
        System.out.println(max);
    }

    public void test_iPsMostVisits(String filename) {
        
        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, Integer> counts = log.countVisitesPerIP();
        ArrayList <String> addresses = log.iPsMostVisits(counts);
        System.out.println(addresses);
    }

    public void test_iPsForDays(String filename) {

        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, ArrayList<String>> iPsForDays = log.iPsForDays();
        System.out.println(iPsForDays);
    }

    public void test_dayWithMostIPVisits(String filename) {

        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, ArrayList<String>> iPsForDays = log.iPsForDays();
        String max = log.dayWithMostIPVisits(iPsForDays);
        System.out.println(max);
    }

    public void test_iPsWithMostVisitsOnDay( String date, String filename) {

        LogAnalyzer log = new LogAnalyzer();
        log.readFile(filename);
        HashMap <String, ArrayList<String>> iPsForDays = log.iPsForDays();
        ArrayList<String> iPs = log.iPsWithMostVisitsOnDay(iPsForDays, date);
        System.out.println(iPs);

    }
    
    public static void main (String[] args ) {
        
        Tester tester = new Tester();

        /* 
        System.out.println("------------------ LogEntry");
        tester.test_logEntry();
        
        System.out.println("------------------ Analyzer");
        tester.test_logAnalyzer();
        
        System.out.println("------------------ Unique IPs");
        tester.test_uniqueIP("data\\short-test_log");
        
        System.out.println("------------------ Status Code Higher Than 200");
        tester.test_allHigherThanNum();
        
        System.out.println("------------------ IP Visits On Day");
        // Test case 1, should return 2
        tester.test_uniqueIPVisitsOnDay("Sep 14", "data\\weblog-short_log");
        // Test case 2, should return 3
        tester.test_uniqueIPVisitsOnDay("Sep 30", "data\\weblog-short_log");
        // Quiz
        tester.test_uniqueIPVisitsOnDay("Mar 17", "weblog1_log");
        
        System.out.println("------------------ Unique IPs in Status Range");
        // Test case 1, should return 4
        tester.test_uniqueIPsInRange(200, 299, "data\\short-test_log");
        // Test case 2, should return 2
        tester.test_uniqueIPsInRange(300, 399, "data\\short-test_log");
        // Quiz
        tester.test_uniqueIPsInRange(300, 399, "data\\weblog1_log");
        
        System.out.println("------------------ Read File With Unique Status");
        tester.test_fileWithUniqueStatus("data\\short-test_log");

        System.out.println("------------------ Count Visits Per IP");
        tester.test_countVisitsPerIP("data\\short-test_log");
        
        System.out.println("------------------ Most Number Visits By IP");
        tester.test_mostNumberVisitsByIP("data\\weblog3-short_log");
        
        System.out.println("------------------ IPs Most Visits");
        tester.test_iPsMostVisits("data\\weblog3-short_log");
        
        System.out.println("------------------ IPs For Days");
        tester.test_iPsForDays("data\\weblog3-short_log");
        
        System.out.println("------------------ Day With Most IPs Visits");
        tester.test_dayWithMostIPVisits("data\\weblog3-short_log");
        
        System.out.println("------------------ IPs With Most Visits On Day");
        tester.test_iPsWithMostVisitsOnDay("Sep 30", "data\\weblog3-short_log");
        
        // Quiz
        tester.test_mostNumberVisitsByIP("data\\weblog1_log");
        tester.test_iPsMostVisits("data\\weblog1_log");
        tester.test_dayWithMostIPVisits("data\\weblog1_log");
        
        // Graded Quiz
        tester.test_iPsWithMostVisitsOnDay("Mar 17", "data\\weblog1_log");
        tester.test_uniqueIP("data\\weblog2_log");
        tester.test_uniqueIPVisitsOnDay("Sep 24", "data\\weblog2_log");
        tester.test_uniqueIPsInRange(200, 299, "data\\weblog2_log");
        tester.test_mostNumberVisitsByIP("data\\weblog2_log");
        tester.test_iPsMostVisits("data\\weblog2_log");
        tester.test_dayWithMostIPVisits("data\\weblog2_log");
        */
        tester.test_iPsWithMostVisitsOnDay("Sep 29", "data\\weblog2_log");
        
    }
}