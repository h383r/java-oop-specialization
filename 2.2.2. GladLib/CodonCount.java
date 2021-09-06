import edu.duke.*;
import java.util.*;

public class CodonCount {
    
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap (int start, String dna) {

        codonMap.clear();
        dna = dna.toUpperCase();
        int codon = (dna.length() - start) / 3;
        
        for (int i = 0; i <= codon - 1; i++) {

            String currentCodon = dna.substring((i * 3) + start, (i * 3) + start + 3);

            if (!codonMap.containsKey(currentCodon)) {
                codonMap.put(currentCodon, 1);
            }
            else {
                codonMap.put(currentCodon, codonMap.get(currentCodon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon () {

        int max = 0;
        String mostCommon = null;

        for (String s: codonMap.keySet()) {

            int count = codonMap.get(s);
            if (count > max) {
                max = count;
                mostCommon = s;
            }
        }
        return mostCommon;
    }

    public void printCodonCounts ( int start, int end) {

        for (String key: codonMap.keySet()) {
            int current = codonMap.get(key);
            if (current >= start && current <= end) {
                System.out.println(key + "    " + current);
            }
        }
    }

    public void tester () {

        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        //String dna = "CGTTCAAGTTCAA";

        int start = 1;
        int end = 7;

        // Reading frame 1
        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in " + codonMap.size() + " unique codons");
        String mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + codonMap.get(mostCommon));
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        printCodonCounts(start, end);

        // Reading frame 2
        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in " + codonMap.size() + " unique codons");
        mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + codonMap.get(mostCommon));
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        printCodonCounts(start, end);

        // Reading frame 3
        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in " + codonMap.size() + " unique codons");
        mostCommon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommon + " with count " + codonMap.get(mostCommon));
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        printCodonCounts(start, end);

    }

    public static void main (String[] args) {
        CodonCount test = new CodonCount();
        test.tester();
    }
}
