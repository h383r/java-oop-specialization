import edu.duke.*;

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

    // Return a StorageResource containing the genes found
    public StorageResource getAllGenes (String dna) {

        StorageResource geneList = new StorageResource();
        int beginIndex = 0;

        while (true) {

            // Find next string after beginIndex
            String currentGene = findGene(dna, beginIndex);

            // If no gene was found, leave this loop
            if (currentGene.isEmpty()) {
                break;
            }
            else {
                // Add gene to geneList
                geneList.add(currentGene);

                // Set beginIndex at the end of currentGene
                beginIndex = dna.indexOf(currentGene, beginIndex) + currentGene.length();
            }
        }
        return geneList;
    }

    public void testGetAllGenes (String dna) {
        
        StorageResource genes = getAllGenes(dna);

        for (String g : genes.data()) {
            System.out.println(g);
        }
    }

    public void test () {
        testGetAllGenes();
    }

    public static void main (String[] args) {
        Part1 p1 = new Part1();
        p1.test();
    }
}