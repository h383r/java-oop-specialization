public class Part2 {

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {

        // Set all to uppercase without modify the original String
        String dnaUP = dna.toUpperCase();
        String startCodonUP = startCodon.toUpperCase();
        String stopCodonUP = stopCodon.toUpperCase();

        // Index position of the start codon “ATG”
        int start = dnaUP.indexOf(startCodonUP);

        // If there is no “ATG” return the empty string
        if (start == -1) {
            return "";
        }

        // Index position of the stop codon “TAA" after the “ATG” that was found
        int stop = dnaUP.indexOf(stopCodonUP, start + 3);

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
        String testCase1 = "ccccccccccccccctaaccc";
        // DNA with no “TAA”
        String testCase2 = "cccatgccccccccccccccc";
        // DNA with no “ATG” or “TAA”
        String testCase3 = "ccccccccccccccccccccc";
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        String testCase4 = "cccatgccccccccctaaccc";
        // DNA with ATG, TAA and the substring between them is not a multiple of 3.
        String testCase5 = "cccatgccccccctaaccc";
        // Quiz1
        String quiz1 = "AAATGCCCTAACTAGATTAAGAAACC";
        
        // Print the DNA strings
        System.out.println("Test Case 1 = " + testCase1);
        System.out.println("Test Case 2 = " + testCase2);
        System.out.println("Test Case 3 = " + testCase3);
        System.out.println("Test Case 4 = " + testCase4);
        System.out.println("Test Case 5 = " + testCase5);
        System.out.println("Quiz 1 = " + quiz1);

        // See if there is a gene by calling findSimpleGene with this string as the parameter.
        String result1 = findSimpleGene(testCase1, "ATG", "TAA");
        String result2 = findSimpleGene(testCase2, "ATG", "TAA");
        String result3 = findSimpleGene(testCase3, "ATG", "TAA");
        String result4 = findSimpleGene(testCase4, "ATG", "TAA");
        String result5 = findSimpleGene(testCase5, "ATG", "TAA");
        String quiz1result = findSimpleGene(quiz1, "ATG", "TAA");

        // If a gene exists following our algorithm, then print the gene, otherwise print the empty string.
        System.out.println("Test Case 1 Result = " + result1 );
        System.out.println("Test Case 2 Result = " + result2 );
        System.out.println("Test Case 3 Result = " + result3 );
        System.out.println("Test Case 4 Result = " + result4 );
        System.out.println("Test Case 5 Result = " + result5 );
        System.out.println("Quiz 1 Result = " + quiz1result );
    }

    public static void main (String[] args) {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}