import edu.duke.*;
import org.apache.commons.csv.*;

public class exportdata {
    
    // Returns a string of information about the country 
    public String countryInfo (CSVParser parser, String country) {

        // For each row in CSV File
        for (CSVRecord record : parser) {

            String target = record.get("Country");

            if (target.contains(country)) {

                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                
                return target + ":" + exports + ":" + value;
            }
        }
        return "NOT FOUND";
    }

    //  Prints the names of all the countries that have both exportItem1 ​and exportItem2 ​as export items
    public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {

            String exports = record.get("Exports");

            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                
                System.out.println(record.get("Country"));

            }
        }
    }

    // Returns the number of countries that export export Item
    public int numberOfExporters (CSVParser parser, String exportItem) {

        int total = 0;

        for (CSVRecord record : parser) {

            String exports = record.get("Exports");

            if (exports.contains(exportItem)) {
                total++;
            }
        }
        return total;
    }

    // Prints the names of countries and their Value amount for all countries whose Value (dollars) string is larger than the amount string
    public void bigExporters (CSVParser parser, String amount) {

        for (CSVRecord record : parser) {

            String value = record.get("Value (dollars)");
            String country = record.get("Country");

            if (value.length() > amount.length()) {

                System.out.println(country + " " + value);

            }
        }
    }

    public void tester () {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        //System.out.println(countryInfo(parser, "Germany"));
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //System.out.println(numberOfExporters(parser, "gold"));
        //bigExporters(parser, "$999,999,999");
    }

    public static void main (String[] args) {
        exportdata exportdata = new exportdata();
        exportdata.tester();
    }
}