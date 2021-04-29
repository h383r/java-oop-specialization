//import java.io.*;
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

            int numBorn = Integer.parseInt(currentRow.get(2));

            if(numBorn <= 100) {
                System.out.print("Name: " + currentRow.get(0));
                System.out.print(" Gender: " + currentRow.get(1));
                System.out.print(" Num Born: " + currentRow.get(2));
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
        int totalBirths = 0;
        int totalM = 0;
        int totalF = 0;

        for (CSVRecord currentRow : parser) {
            
            int numBorn = Integer.parseInt(currentRow.get(2));
            totalBirths = totalBirths + numBorn;

            if(currentRow.get(1).equals("M")) {
                totalM = totalM + numBorn;
            }
            else {
                totalF = totalF + numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total male = " + totalM);
        System.out.println("total female = " + totalF);
    }

    //ASSIGNMENT ===============================================================

    /**
     * Rank of the name in the file for the given gender
     * @param year
     * @param name
     * @param gender
     * @return Rank of the name. (-1 if the name is not in the file)
     */
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;

        for (CSVRecord currentRow : parser) {

            
        }



        /*
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false); // False = no header row

        for (CSVRecord currentRow : parser) {

            int numBorn = Integer.parseInt(currentRow.get(2));

            if(numBorn <= 100) {
                System.out.print("Name: " + currentRow.get(0));
                System.out.print(" Gender: " + currentRow.get(1));
                System.out.print(" Num Born: " + currentRow.get(2));
                System.out.print("\n");
            }
        }
        */
        return rank;
    }


    //getName



    //whatIsNameInYear



    //yearOfHighestRank



    //getAverageRank



    //getTotalBirthsRankedHigher    




    
    public static void main(String[] args) {
        names names = new names();

        names.printNames();
        //names.totalBirths();

    }
}