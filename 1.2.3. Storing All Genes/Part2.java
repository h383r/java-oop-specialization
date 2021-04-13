public class Part2 {

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

    public void testCgRatio () {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    
    // Number of times the codon CTG appears in dna
    public int countCTG (String dna) {

        // Set all to uppercase
        dna = dna.toUpperCase();

        int beginIndex = 0;
        int ctg = 0;
        
        while (true) {

            // Find next occurrence after beginIndex
            int index = dna.indexOf("CTG", beginIndex);
            
            // If not "CTG", leave the loop
            if(index == -1) {
                break;
            }
            else {
                // Increment counter, update currIndex
                ctg = ctg + 1;

                // Set beginIndex at the end of currIndex
                beginIndex = index + 3;
            }
        }
        return ctg;
    }

    public void testCountCTG() {
        System.out.println(countCTG("XXCTGXXXXCTGXXXXCTGXXXCTG"));
    }

    public static void main (String[] args) {
        Part2 p2 = new Part2();
        p2.testCgRatio();
        p2.testCountCTG();
    }
}