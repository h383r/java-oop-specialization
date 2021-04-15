import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/*
import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
*/

public class weatherdata {
    
    // returns the CSVRecord with the coldest temperature in the file
    public CSVRecord coldestHourInFile (CSVParser parser) {
    
        CSVRecord coldest = null;

        for (CSVRecord row : parser) {

            if (coldest == null) {
                coldest = row;
            }
            else {
                double currentTemp = Double.parseDouble(row.get("TemperatureF"));
                double minimumTemp = Double.parseDouble(coldest.get("TemperatureF"));

                if (currentTemp == -9999) {
                    // not a valid reading
                    break;
                }
                else if (currentTemp < minimumTemp) {
                    coldest = row;
                }
            }
        }
        return coldest;
    }

    public void coldestHourInFileTest () {
        FileResource fr = new FileResource("data/nc_weather/2015/weather-2015-01-02.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
    }

    public String fileWithColdestTemperature () {
        
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldest = null;
        String filename = null;

        





        return filename;
    }


     
    /*
        comentar:
        CSVRecord =>
        getCSVParser =>
    */


    public CSVRecord hottestHourInFile (CSVParser parser) {
        
        CSVRecord largestSoFar = null;
        
        for (CSVRecord currentRow : parser) {
            
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    
    public void hottestHourInFileTest () {
        FileResource fr = new FileResource("data/nc_weather/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestHourInManyFiles () {

        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            
            if (largestSoFar == null) {
                largestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                
                if (currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    
    public void hottestHourInManyFilesTest () {
        CSVRecord largest = hottestHourInManyFiles();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }

    /*
    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {

        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    */


    public static void main (String[] args) {
        weatherdata weatherdata = new weatherdata();
        
        weatherdata.hottestHourInFileTest();
        //weatherdata.coldestHourInFileTest();
        //weatherdata.hottestHourInManyFilesTest();
           
    }    
}