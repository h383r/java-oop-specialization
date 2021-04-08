public class Part3 {
    
    // Returns true if stringa appears at least twice in stringb
    public boolean twoOccurrences(String stringa, String stringb){
           
        int ocurrence1 = stringb.indexOf(stringa);
        
        if (ocurrence1 != -1) {
            int ocurrence2 = stringb.indexOf(stringa, ocurrence1 + 1);
            if (ocurrence2 != -1) {
                return true;
            }
        }   
        else {
            return false;
        }
        return false;
    }

    // Returns the part of stringb that follows stringa
    public String lastPart(String stringa, String stringb) {

        int ocurrence1 = stringb.indexOf(stringa);
        int beginIndex = ocurrence1 + stringa.length(); 
        int endIndex = stringb.length();

        if (ocurrence1 != -1) {

            // Substring after first ocurrence
            String lastPart = stringb.substring(beginIndex, endIndex);
            
            return lastPart;
        }
        else {
            return stringb;
        }
    }

    public void testing() {

        String stringa2 = "a";
        String stringb2 = "banana";
        boolean test2 = twoOccurrences(stringa2, stringb2);
        
        System.out.println("string a = " + stringa2);
        System.out.println("string b = " + stringb2);
        System.out.println("two occurrences of string a in string b = " + test2);
        System.out.println("----------------------------------------------------");

        String stringa3 = "atg";
        String stringb3 = "“ctgtatgta”";
        boolean test3 = twoOccurrences(stringa3, stringb3);
        
        System.out.println("string a = " + stringa3);
        System.out.println("string b = " + stringb3);
        System.out.println("two occurrences of string a in string b = " + test3);
        System.out.println("----------------------------------------------------");

        String stringa4 = "an";
        String stringb4 = "banana";
        String result4 = lastPart(stringa4, stringb4);

        System.out.println("The part of the string after " + stringa4 + " in " + stringb4 + " is " + result4);
        System.out.println("----------------------------------------------------");

        String stringa5 = "zoo";
        String stringb5 = "forest";
        String result5 = lastPart(stringa5, stringb5);

        System.out.println("The part of the string after " + stringa5 + " in " + stringb5 + " is " + result5);
        System.out.println("----------------------------------------------------");

    }

    public static void main (String[] args) {
        Part3 p3 = new Part3();
        p3.testing();
    }
}