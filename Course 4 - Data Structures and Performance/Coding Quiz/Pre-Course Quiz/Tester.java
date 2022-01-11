public class Tester {

    public static void main (String[] args) {
        Person p = new Person("Clarissa");
        System.out.println(p);
        
        // Compiler Error
        // cannot convert from Student to Person
        Person s = new Student("Jose",12345);
        System.out.println(s);
    }
}