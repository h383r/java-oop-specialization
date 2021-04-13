public class Part1 {

    public String findSimpleGene(String dna) {

        // Index position of the start codon “ATG”
        int start = dna.indexOf("ATG");

        // If there is no “ATG” return the empty string
        if (start == -1) {
            return "";
        }

        // Index position of the stop codon “TAA" after the “ATG” that was found
        int stop = dna.indexOf("TAA", start + 3);

        // If there is no such “TAA” return the empty string
        if (stop == -1) {
            return "";
        }

        // Substring between the “ATG” and “TAA”
        String gen = dna.substring(start, stop + 3);

        // If the substring length is a multiple of 3 return the substring
        if (gen.length() % 3 == 0) {
            return gen;
        }

        // If is not a multiple of 3 return the empty string
        else {
            return "";
        }
    }

    public void testSimpleGene() {
        
        // Test Cases

        // DNA with no “ATG”
        String testCase1 = "CCCCCCCCCCCCCCCTAACCC";
        // DNA with no “TAA”
        String testCase2 = "CCCATGCCCCCCCCCCCCCCC";
        // DNA with no “ATG” or “TAA”
        String testCase3 = "CCCCCCCCCCCCCCCCCCCCC";
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        String testCase4 = "CCCATGCCCCCCCCCTAACCC";
        // DNA with ATG, TAA and the substring between them is not a multiple of 3.
        String testCase5 = "CCCATGCCCCCCCTAACCC";
        
        // Print the DNA strings
        System.out.println("Test Case 1 = " + testCase1);
        System.out.println("Test Case 2 = " + testCase2);
        System.out.println("Test Case 3 = " + testCase3);
        System.out.println("Test Case 4 = " + testCase4);
        System.out.println("Test Case 5 = " + testCase5);

        // See if there is a gene by calling findSimpleGene with this string as the parameter.
        String result1 = findSimpleGene(testCase1);
        String result2 = findSimpleGene(testCase2);
        String result3 = findSimpleGene(testCase3);
        String result4 = findSimpleGene(testCase4);
        String result5 = findSimpleGene(testCase5);

        // If a gene exists following our algorithm, then print the gene, otherwise print the empty string.
        System.out.println("Test Case 1 Result = " + result1 );
        System.out.println("Test Case 2 Result = " + result2 );
        System.out.println("Test Case 3 Result = " + result3 );
        System.out.println("Test Case 4 Result = " + result4 );
        System.out.println("Test Case 5 Result = " + result5 );
    }

    public static void main (String[] args) {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}