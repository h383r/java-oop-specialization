package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Sorting {

    public int[] swap (int[] values, int posA, int posB) {

        int tempValue;
        tempValue = values[posA];
        values[posA] = values[posB];
        values[posB] = tempValue;

        return values;
    }

    public void selectionSort (int[] values) {

        for ( int i = 0; i < values.length - 1; i++) {

            int indexMin = i;

            for ( int j = i + 1; j < values.length; j++) {

                if ( values[j] < values[indexMin]) {
                    indexMin = j;
                }
            }
            swap ( values, indexMin, i);
            
            System.out.print("Iteracion " + i + ": ");
            for ( int e = 0; e < values.length; e++) {
            	System.out.print(values[e] + " ");
            }
            System.out.println("");
            
        }
    }
        
    public void insertionSort (int[] values) {

        int currInd;

        for ( int pos = 1; pos < values.length; pos++) {

            currInd = pos;

            while ( currInd > 0 && values[currInd] < values[currInd - 1]) {
                swap(values, currInd, currInd - 1);
                currInd = currInd - 1;
            }
            
            System.out.print("Iteracion " + pos + ": ");
            for ( int e = 0; e < values.length; e++) {
            	System.out.print(values[e] + " ");
            }
            System.out.println("");
        }
    }

    public ArrayList<Integer> randomData () {

        Random random = new Random();
        ArrayList<Integer> data = new ArrayList<Integer>();

        for (int i = 0; i < 5; i++) {
            data.add(random.nextInt(100));
        }

        return data;
    }

    public static void main (String[] args) {

        Sorting sort = new Sorting();
        
        System.out.println("Selection Sort Test");

        int[] valuesA = {8 , 12  , 45  , 58  , 22  , 18  , 43 , 30};
        sort.selectionSort(valuesA);

        System.out.println("Insertion Sort Test");

        int[] valuesB = {8 , 12  , 45  , 58  , 22  , 18  , 43 , 30};
        sort.insertionSort(valuesB);

        
        /*
        System.out.println("Built In Sort Test");

        ArrayList<Integer> randomData = sort.randomData();
        
        Collections.sort(randomData);

        for ( int i = 0; i < randomData.size(); i++) {
            System.out.println(randomData.get(i));
        }
        */

    }

}
