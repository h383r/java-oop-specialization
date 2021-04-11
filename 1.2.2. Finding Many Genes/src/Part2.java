public class Part2 {

    // Returns an integer indicating how many times stringa appears in stringb
    public int howMany (String stringa, String stringb) {

        // First ocurrence
        int beginIndex = stringb.indexOf(stringa);
        int ocurrences = 0;

        while(beginIndex != -1) {
            beginIndex = stringb.indexOf(stringa, beginIndex + stringa.length());

            ocurrences = ocurrences + 1;

        }
        return ocurrences;
    }
    
    public void testHowMany () {
        
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        
        int result1 = howMany(stringa1, stringb1);
        
        System.out.println(stringa1);
        System.out.println(stringb1);
        System.out.println("Ocurrences = " + result1);
        
        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        
        int result2 = howMany(stringa2, stringb2);
        
        System.out.println(stringa2);
        System.out.println(stringb2);
        System.out.println("Ocurrences = " + result2);
        
    }
    
    
    public static void main (String[] args) {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}