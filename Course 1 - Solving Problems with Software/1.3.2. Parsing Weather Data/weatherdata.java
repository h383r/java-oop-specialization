import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class weatherdata {
    
    /**
     * Search for the highest temperature recorded in file
     * @param parser CSVParser
     * @return Row with highest temperature recorded in the file
     */
    public CSVRecord hottestHourInFile(CSVParser parser) {
        
        CSVRecord highestTemperature = null;
        
        for (CSVRecord currentRow : parser) {
            
            if (highestTemperature == null) {
                highestTemperature = currentRow;
            }
            else {
                double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
                double tempValue = Double.parseDouble(highestTemperature.get("TemperatureF"));
                
                if (currentTemperature > tempValue) {
                    highestTemperature = currentRow;
                }
            }
        }
        return highestTemperature;
    }
    
    public void hottestHourInFileTest() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = hottestHourInFile(parser);
        System.out.println("hottest temperature was " + csv.get("TemperatureF") + " at " + csv.get("TimeEST"));
    }
    
    /**
     * Search for the highest temperature recorded in many files
     * @return Row with highest temperature recorded in selected files
     */
    public CSVRecord hottestHourInManyFiles() {
        
        CSVRecord highestTemperature = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File currentFile : dr.selectedFiles()) {
            
            FileResource fr = new FileResource(currentFile);
            CSVParser parser = fr.getCSVParser();
            CSVRecord highestTemperatureRow = hottestHourInFile(parser);
            
            if (highestTemperature == null) {
                highestTemperature = highestTemperatureRow;
            }
            else {
                double currentTemperature = Double.parseDouble(highestTemperatureRow.get("TemperatureF"));
                double tempValue = Double.parseDouble(highestTemperature.get("TemperatureF"));
                
                if (currentTemperature > tempValue) {
                    highestTemperature = highestTemperatureRow;
                }
            }
        }
        return highestTemperature;
    }
    
    public void hottestHourInManyFilesTest() {
        CSVRecord csv = hottestHourInManyFiles();
        System.out.println("hottest temperature was " + csv.get("TemperatureF") + " at " + csv.get("DateUTC"));
    }

    /**
     * Search for the lowest temperature in the file
     * @param parser CSVParser
     * @return Row with the lowest temperature in the file
     */
    public CSVRecord coldestHourInFile(CSVParser parser) {
    
        CSVRecord lowestTemperature = null;

        for (CSVRecord currentRow : parser) {

            if (lowestTemperature == null) {
                lowestTemperature = currentRow;
            }
            else {
                double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
                double tempValue = Double.parseDouble(lowestTemperature.get("TemperatureF"));

                if (currentTemperature == -9999) {
                    // not a valid reading
                    break;
                }
                else if (currentTemperature < tempValue) {
                    lowestTemperature = currentRow;
                }
            }
        }
        return lowestTemperature;
    }

    public void coldestHourInFileTest() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = coldestHourInFile(parser);
        System.out.println("coldest temperature was " + csv.get("TemperatureF") + " at " + csv.get("TimeEST"));
    }

    /**
     * Search for the file that has the coldest temperature
     * @return Name of the file from selected files that has the coldest temperature
     */
    public String fileWithColdestTemperature() {
        
        DirectoryResource dr = new DirectoryResource();
        String filename = "";

        Double lowestTemperature = null;

        // For each file in directory resource
        for (File currentFile : dr.selectedFiles()) {

            FileResource fr = new FileResource(currentFile);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestTemperatureRow = coldestHourInFile(parser);

            // Coldest temperature in file
            double lowestTemperatureInFile = Double.parseDouble(lowestTemperatureRow.get("TemperatureF"));

            if (filename == "") {
                filename = currentFile.getPath();
                lowestTemperature = lowestTemperatureInFile;
            }
            else if (lowestTemperature > lowestTemperatureInFile) {
                filename = currentFile.getPath();
                lowestTemperature = lowestTemperatureInFile;
            }
        }
        System.out.println(filename);
        return filename;
    }

    public void fileWithColdestTemperatureTest() {
        
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = coldestHourInFile(parser);

        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temperature on that day was " + csv.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");

        // Restart parser
        parser = fr.getCSVParser();

        for (CSVRecord row : parser) {
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }

    /**
     * Search for the lowest humidity value in the file
     * @param parser CSVParser
     * @return Row with lowest humidity recorded in the file
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord lowestHumidity = null;

        for (CSVRecord currentRow : parser) {

            if (lowestHumidity == null) {
                lowestHumidity = currentRow;
            }
            else {
                String currentValue = currentRow.get("Humidity");
                if (currentValue == "N/A") {
                    break;
                }
                else {
                    double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                    double tempValue = Double.parseDouble(lowestHumidity.get("Humidity"));

                    if (currentHumidity < tempValue) {
                        lowestHumidity = currentRow;
                    }
                }
            }
        }
        return lowestHumidity;
    }

    public void lowestHumidityInFileTest() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    /**
     * Search for the lowest humidity recorded in many files
     * @return Row with lowest humidity recorded in selected files
     */
    public CSVRecord lowestHumidityInManyFiles() {

        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {

            FileResource fr = new FileResource(currentFile);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestHumidityRow = lowestHumidityInFile(parser);

            if (lowestHumidity == null) {
                lowestHumidity = lowestHumidityRow;
            }
            else {
                String currentValue = lowestHumidityRow.get("Humidity");
                if (currentValue == "N/A") {
                    break;
                }
                else {
                    double currentHumidity = Double.parseDouble(lowestHumidityRow.get("Humidity"));
                    double tempValue = Double.parseDouble(lowestHumidity.get("Humidity"));

                    if (currentHumidity < tempValue) {
                        lowestHumidity = lowestHumidityRow;
                    }
                }
            }
        }
        return lowestHumidity;
    }

    public void lowestHumidityInManyFilesTest() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    /**
     * Calculate average temperature in the file
     * @param parser CSVParser
     * @return Average temperature in the file
     */
    public double averageTemperatureInFile(CSVParser parser) {

        double totalTemperature = 0.0;
        int totalValues = 0;

        for (CSVRecord currentRow : parser) {
            double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemperature = totalTemperature + currentTemperature;
            totalValues = totalValues + 1;
        }
        return totalTemperature / totalValues;
    }

    public void averageTemperatureInFileTest() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }

    /**
     * Searc for temperatures when the humidity was greater than or equal to value.
     * @param parser CSVParser
     * @param value Humidity greater than or equal to
     * @return Temperature when humidity was greather or equal to value
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {

        double totalTemperature = 0.0;
        int totalValues = 0;

        for (CSVRecord currentRow : parser) {
            
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemperature = Double.parseDouble(currentRow.get("TemperatureF"));

            if ((currentRow.get("Humidity")) == "N/A" || currentTemperature == -9999) {
                break;
            }
            else if (currentHumidity >= value){
                totalTemperature = totalTemperature + currentTemperature;
                totalValues = totalValues + 1;
            }    
        }
        return totalTemperature / totalValues;
    }

    public void averageTemperatureWithHighHumidityInFileTest() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        double test = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(test)) {
            System.out.println("No temperatures with that humidity");
        }
        else {   
            System.out.println("Average Temp when high Humidity is " + test);
        }
    }

    public static void main(String[] args) {
        weatherdata weatherdata = new weatherdata();
        
        //weatherdata.hottestHourInFileTest();
        //weatherdata.coldestHourInFileTest();
        //weatherdata.hottestHourInManyFilesTest();
        //weatherdata.fileWithColdestTemperatureTest();
        //weatherdata.lowestHumidityInFileTest();
        //weatherdata.lowestHumidityInManyFilesTest();
        //weatherdata.averageTemperatureInFileTest();
        weatherdata.averageTemperatureWithHighHumidityInFileTest();

    }    
}