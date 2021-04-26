//import java.io.*;
import edu.duke.*;
import org.apache.commons.csv.*;

public class names {

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


    
    public static void main(String[] args) {
        names names = new names();
        //names.printNames();
        names.totalBirths();

    }
}