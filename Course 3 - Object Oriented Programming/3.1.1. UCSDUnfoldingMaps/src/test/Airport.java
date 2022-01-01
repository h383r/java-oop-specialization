public class Airport {

    private String city;
    private String country;
    private String code3;

    public Airport(String city, String country, String code3) {
        this.city = city;
        this.country = country;
        this.code3 = code3;
    }

    // Getters
    
    public String getCity() { 
        return this.city;
    }
    public String getCountry() {
        return this.country;
    }
    public String getCode() {
        return this.code3;
    }

    public int compareTo (Airport other) {
        return (this.getCity()).compareTo(other.getCity());
    }
}

