import edu.duke.*;

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
    
    // Ratio of C’s and G’s in dna
    public float cgRatio (String dna) {
        
        // Set all to uppercase
        dna = dna.toUpperCase();
        int cg = 0;
        
        // For each character in dna
        for (int i = 0; i < dna.length(); i++) {
            
            String character = Character.toString(dna.charAt(i));
            
            // If is "C" or "G", increment counter
            if (character.equals("C") || character.equals("G")) {
                cg = cg + 1;
            }
        } 
        return (float) cg / dna.length();
    }
    
    // Processes all the strings in sr to find out information about them.     
    public void processGenes (StorageResource sr) {

        // print all the Strings in sr that are longer than 9 characters
        int longerThan9 = 0;
        
        System.out.println("============================================================");
        System.out.println("Strings that are longer than 9 characters = ");
        
        for (String str : sr.data()) {
            if (str.length() > 9) {
                longerThan9 = longerThan9 + 1;
                System.out.println(str);
            }
        }
        
        // print the number of Strings in sr that are longer than 9 characters
        System.out.println("============================================================");
        System.out.println("Number of strings in sr that are longer than 9 characters = ");
        System.out.println(longerThan9);
        
        //prints all the Strings that are longer than 60 characters
        int longerThan60 = 0;
        
        System.out.println("============================================================");
        System.out.println("Strings that are longer than 60 characters = ");
        
        for (String str : sr.data()) {
            if (str.length() > 60) {
                longerThan60 = longerThan60 + 1;
                System.out.println(str);
            }
        }
        
        // print the number of Strings in sr that are longer than 60 characters       
        System.out.println("============================================================");
        System.out.println("Number of strings in sr that are longer than 60 characters = ");
        System.out.println(longerThan60);

        // print the Strings in sr whose C-G-ratio is higher than 0.35
        int ratioHigherThan035 = 0;
        
        System.out.println("============================================================");
        System.out.println("Strings in sr whose C-G-ratio is higher than 0.35 = ");
        
        for (String str : sr.data()) {
            double ratio = cgRatio(str);
            if (ratio > 0.35) {
                ratioHigherThan035 = ratioHigherThan035 + 1;
                System.out.println(str);
            }
        }
                
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
		System.out.println("============================================================");
		System.out.println("Number of strings in sr whose C-G-ratio is higher than 0.35 = ");
        System.out.println(ratioHigherThan035);
        
        // print the length of the longest gene in sr
        int longestGene = 0;
        
        for (String string : sr.data()) {
            if (string.length() > longestGene) {
                longestGene = string.length();
            }
        }
        
		System.out.println("============================================================");
        System.out.println("Length of the longest gene in sr = ");
        System.out.println(longestGene);

    }
    
    public void testProcessGenes () {

        FileResource fr = new FileResource(); //"GRch38dnapart.fa"
		String file = fr.asString();
        StorageResource genes = getAllGenes(file);
        processGenes(genes);

    }

    
    public static void main (String[] args) {
        Part3 p3 = new Part3();
        p3.testProcessGenes();
    }
}