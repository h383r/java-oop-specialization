public class Part1 {

    public int findStopCodon (String dna, int beginIndex, String stopCodon) {
        
        int currIndex =  dna.indexOf(stopCodon, beginIndex + 3);

        while (currIndex != -1) {
            
            // If currIndex - startIndex is a multiple of 3
            if ((currIndex - beginIndex) % 3 == 0) {

                // Index of the first occurrence of stopCodon
                return currIndex;    
            }
            // If not, update currIndex
            else {
                currIndex = dna.indexOf(stopCodon, currIndex +1);
            }
        }
        // If there is no such stopCodon, returns the length of the dna strand
        return -1;
    }

    public void testFindStopCodon () {

        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int t1 = 0;

        // 9
        t1 = findStopCodon(dna, 0, "TAA");        
        System.out.println(t1);

        // 21
        t1 = findStopCodon(dna, 9, "TAA");
        System.out.println(t1);

        // 26
        t1 = findStopCodon(dna, 1, "TAA");        
        System.out.println(t1);

        // 26
        t1 = findStopCodon(dna, 0, "TAG");        
        System.out.println(t1);

    }

    public String findGene (String dna, int pos) {

        // Set all to uppercase without modify the original String
        String dnaUP = dna.toUpperCase();

        // Index of the first occurrence of “ATG”
        int beginIndex = dnaUP.indexOf("ATG", pos);

        // If there is no “ATG”
        if (beginIndex == -1) {
            return "";
        }

        // Index of the first occurrence of the stop codon
        int taaIndex = findStopCodon(dnaUP, beginIndex, "TAA");
        int tagIndex = findStopCodon(dnaUP, beginIndex, "TAG");
        int tgaIndex = findStopCodon(dnaUP, beginIndex, "TGA");

        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }        

        // If nothing found
        if (minIndex == -1) {
            return "";
        }

        // String from the “ATG” and the closest stop codon
        return dna.substring(beginIndex, minIndex + 3);    
    }

    public void testFindGene () {

        // DNA with no “ATG”
        String dna1 = "ccccccccccccccccccccc";
        
        // DNA with “ATG” and one valid stop codon
        String dna2 = "cccATGcccccccccTAAccc";
        
        // DNA with “ATG” and multiple valid stop codons
        String dna3 = "cccATGcccTAAcccTAGccc";
        
        //DNA with “ATG” and no valid stop codons
        String dna4 = "cccATGccTAAccccTAGccc";
        
        // DNA with “ATG” no stop codons
        String dna5 = "cccATGccccccccccccccc";

        System.out.println("Gene in dna1 = " + findGene(dna1, 0));
        System.out.println("Gene in dna2 = " + findGene(dna2, 0));
        System.out.println("Gene in dna3 = " + findGene(dna3, 0));
        System.out.println("Gene in dna4 = " + findGene(dna4, 0));
        System.out.println("Gene in dna5 = " + findGene(dna5, 0));

    }

    public void printAllGenes (String dna) {

        int beginIndex = 0;

        while (true) {

            // Find next string after beginIndex
            String currentGene = findGene(dna, beginIndex);

            // If no gene was found, leave this loop
            if (currentGene.isEmpty()) {
                break;
            }
            else {
                // Print current gene found
                System.out.println(currentGene);

                // Set beginIndex at the end of currentGene
                beginIndex = dna.indexOf(currentGene, beginIndex) + currentGene.length();
            }
        }
    }

    public void testPrintAll () {

        String dna = "atgatctaatttatgctgctgcaacggtgaagaatgatctaatttatgctgctgcaacggtgaaga";
        printAllGenes(dna);

    }

    public static void main (String[] args) {
        Part1 p1 = new Part1();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        p1.testPrintAll();

    }       
}