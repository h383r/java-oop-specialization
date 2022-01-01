public class AirportTest {

    public String findAirportCodeLinear(String toFind, Airport[] airports) {
        
        int index = 0;

        while (index < airports.length) {
            
            Airport airport = airports[index];
            
            if (toFind.equals(airport.getCity())) {
                return airport.getCode();
            }
            index++;
        }
        return null;
    }

    public String findAirportCodeBinary(String toFind, Airport[] airports) {
        
        int low = 0;
        int high = airports.length;
        int mid;

        while (low <= high) {

            mid = (low + high) / 2;

            int compare = toFind.compareTo(airports[mid].getCity());
        
            if (compare < 0) {
                high = mid - 1;
            }
            else if (compare > 0) {
                low = mid + 1;
            }
            else {
                return airports[mid].getCode();
            }
        }
        return null;
    }
    public static void main (String[] Args) {
             
        Airport[] airports = new Airport[8];

        airports[0] = new Airport("Aagra", "India", "AGR");
        airports[1] = new Airport("Beijing", "China", "PEK");
        airports[2] = new Airport("Chicago", "USA", "ORD");
        airports[3] = new Airport("Essen", "Germany", "ESS");
        airports[4] = new Airport("Lagos", "Nigeria", "LOS");
        airports[5] = new Airport("Mendoza", "Argentina", "MDZ");
        airports[6] = new Airport("Quito", "Ecuador", "UIO");
        airports[7] = new Airport("Sydney", "Australia", "SYD");
        
        AirportTest test = new AirportTest();

        System.out.println(test.findAirportCodeLinear("Mendoza", airports));
        System.out.println(test.findAirportCodeBinary("Mendoza", airports));
        
    }
}
