public class TG {

    public void howMany() {
        System.out.println("start");
        String dna = "CTGCCTGCATGATCGTA";
        int pos = dna.indexOf("TG");
        int count = 0;

        while (pos >= 0) {
            count = count + 1;
            pos = dna.indexOf("TG",pos + 2);
        }

        System.out.println(count);
    }   
    
    public static void main (String[] args) {
        TG TG = new TG();
        TG.howMany();
    }
}