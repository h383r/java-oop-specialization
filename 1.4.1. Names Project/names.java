import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class names {

    /**
     * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
     */
    public void printNames() {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false); // False = no header row

        for (CSVRecord currentRow : parser) {

            int currentBirths = Integer.parseInt(currentRow.get(2));

            if(currentBirths <= 100) {
                System.out.print("Name: " + currentRow.get(0));
                System.out.print(", Gender: " + currentRow.get(1));
                System.out.print(", Num Born: " + currentRow.get(2));
                System.out.print("\n");
            }
        }
    }

    /**
     * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
     */
    public void totalBirths() {

        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int birthsTotal = 0;
        int birthsMale = 0;
        int birthsFemale = 0;
        int namesMale = 0;
        int namesFemale = 0;
        int namesTotal = 0;

        for (CSVRecord currentRow : parser) {
            
            int currentBirths = Integer.parseInt(currentRow.get(2));
            birthsTotal = birthsTotal + currentBirths;

            if(currentRow.get(1).equals("M")) {
                birthsMale = birthsMale + currentBirths;
                namesMale = namesMale + 1;
            }
            else {
                birthsFemale = birthsFemale + currentBirths;
                namesFemale = namesFemale + 1;
            }
        }
        namesTotal = namesMale + namesFemale;

        System.out.println("total births = " + birthsTotal);
        System.out.println("total births male = " + birthsMale);
        System.out.println("total births female = " + birthsFemale);

        System.out.println("total names = " + namesTotal);
        System.out.println("total names male = " + namesMale);
        System.out.println("total names female = " + namesFemale);
    }

    //ASSIGNMENT ===============================================================

    /**
     * Rank of the name in the file for the given gender (-1 if the name is not in the file).
     * @param year
     * @param name
     * @param gender
     * @return 
     */
    public int getRank(int year, String name, String gender) {

        //FileResource fr = new FileResource("data/us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        
        CSVParser parser = fr.getCSVParser(false);
        
        int rank = 0;

        for (CSVRecord currentRow : parser) {

            String currentName = currentRow.get(0);
            String currentGender = currentRow.get(1);
            
            if (currentGender.equals(gender)) {
                rank = rank + 1;
                if (currentName.equals(name)) {
                    return rank;
                }    
            }
        }
        return -1;
    }

    /**
     * Returns the name of the person in the file at this rank, for the given gender.
     * @param year
     * @param rank
     * @param gender
     * @return
     */
    public String getName(int year, int rank, String gender) {

        //FileResource fr = new FileResource("data/us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");

        CSVParser parser = fr.getCSVParser(false);
        
        String name = "";
        int currentRank = 0;

        for (CSVRecord currentRow : parser) {

            String currentName = currentRow.get(0);
            String currentGender = currentRow.get(1);

            if (currentGender.equals(gender)) {
                currentRank = currentRank + 1;
                if (currentRank == rank) {
                    name = currentName;
                }
            }
        }
        
        if (name == "") {
            return "NO NAME";
        }
        else {
            return name;
        }
    }

    /**
     * Determines what name would have been named if they were born in a different year based on the same popularity.
     * @param name
     * @param year
     * @param newYear
     * @param gender
     * @return 
     */
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        return name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".";
    }

    /**
     * Selects a range of files and returns the year with the highest rank for the name and gender.
     * @param name
     * @param gender
     * @return
     */
    public int yearOfHighestRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int yearOfHighestRank = 0;
        int highestRank = 0;

        for (File currentFile : dr.selectedFiles()) {
            
            String fileName = currentFile.getName();
            int currentYear = Integer.parseInt(fileName.substring(3,7));
            int currentRank = getRank(currentYear, name, gender);

            if (currentRank != -1) {
                if (highestRank == 0 || currentRank < highestRank) {
                    yearOfHighestRank = currentYear;
                    highestRank = currentRank;
                }
            }
        }    
        if (yearOfHighestRank == 0) {
            return -1; 
        }
        else {
            return yearOfHighestRank;
        }
    }

    /**
     * Selects a range of files and returns the average rank of the name and gender.
     * @param name
     * @param gender
     * @return
     */
    public double getAverageRank(String name, String gender) {

        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0.0;
        int totalFiles = 0;

        for (File currentFile : dr.selectedFiles()) {

            String fileName = currentFile.getName();
            int currentYear = Integer.parseInt(fileName.substring(3,7));
            int currentRank = getRank(currentYear, name, gender);

            totalRank = totalRank + currentRank;
            totalFiles = totalFiles + 1;
        }
        if (totalRank == 0) {
            return -1;
        }
        else {
            return totalRank / totalFiles;
        }
    }

    /**
     * Returns the total number of births of those names with the same gender and same year who are ranked higher than name.
     * @param year
     * @param name
     * @param gender
     * @return
     */
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {

        //FileResource fr = new FileResource("data/us_babynames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");

        CSVParser parser = fr.getCSVParser(false);

        int rank = 0;
        int totalBirths = 0;
        
        for (CSVRecord currentRow: parser) {
            
            String currentName = currentRow.get(0);
            String currentGender = currentRow.get(1);
            int currentBirths = Integer.parseInt(currentRow.get(2));
            
            if (currentGender.equals(gender)) {
                if (currentName.equals(name)) {
                    rank = 1;
                    break;
                }
                totalBirths = totalBirths + currentBirths;
            }
        }
        
        if (rank ==1) {
            return totalBirths;
        }
        else {
            return -1;
        }
    }
    
    public static void main(String[] args) {
        names names = new names();

        //Test Cases
        names.printNames();
        //names.totalBirths();
        
        //What is the rank of the girl’s name “Emily” in 1960?
        //System.out.println(names.getRank(1960, "Emily", "F"));
    
        //What is the rank of the boy’s name “Frank” in 1971?
        //System.out.println(names.getRank(1971, "Frank", "M"));
    
        //What is the girl’s name of rank 350 in 1980?
        //System.out.println(names.getName(1980, 350, "F"));
    
        //What is the boy’s name of rank 450 in 1982?
        //System.out.println(names.getName(1982, 450, "M"));
    
        //What name in 2014 had the same rank that "Susan" had in 1972?
        //System.out.println(names.whatIsNameInYear("Susan", 1972, 2014, "F"));
    
        //What name in 2014 had the same rank that "Owen" had in 1974?
        //System.out.println(names.whatIsNameInYear("Owen", 1974, 2014, "M"));
    
        //In which year from 1880 to 2014 does the girl’s name "Genevieve" have the highest rank?
        //System.out.println(names.yearOfHighestRank("Genevieve", "F"));
    
        //In which year from 1880 to 2014 does the boy’s name "Mich" have the highest rank?
        //System.out.println(names.yearOfHighestRank("Mich", "M"));
    
        //What is the average rank of the girl’s name "Susan" over all the data files?
        //System.out.println(names.getAverageRank("Susan", "F"));
    
        //What is the average rank of the boy's name "Robert" over all the data files?
        //System.out.println(names.getAverageRank("Robert", "M"));
    
        //What is the total number of girls born in 1990 with names ranked higher than the girl's name "Emily" in 1990?
        //System.out.println(names.getTotalBirthsRankedHigher(1990, "Emily", "F"));

        //What is the total number of boys born in 1990 with names ranked higher than the boy's name "Drew" in 1990?
        //System.out.println(names.getTotalBirthsRankedHigher(1990, "Drew", "M"));
        
    }
}