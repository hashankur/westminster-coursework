public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public void getDetails() {
        System.out.println("\nTicket for " + name + " " + surname + " (" + email + ")");
    }
}
