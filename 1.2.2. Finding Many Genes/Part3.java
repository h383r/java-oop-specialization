public class Part3 {
    
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
    
    // Count how many genes are in a strand of DNA.
    public int countGenes (String dna) {

        int beginIndex = 0;
        int countGenes = 0;

        while (true) {

            // Find next string after beginIndex
            String currentGene = findGene(dna, beginIndex);

            // If no gene was found, leave this loop
            if (currentGene.isEmpty()) {
                break;
            }
            else {
                // Gene found, increment counter
                countGenes = countGenes + 1;

                // Set beginIndex at the end of currentGene
                beginIndex = dna.indexOf(currentGene, beginIndex) + currentGene.length();
            }
        }
        return countGenes;
    }

    public void testCountGenes () {

        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dna));

    }

    public static void main (String[] args) {
        Part3 p3 = new Part3();
        p3.testCountGenes();
    }
}