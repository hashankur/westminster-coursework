public class Director {
    private String name;
    private String surname;
    private int numDirectedMovie;
    private Date dob;

    public Director(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getNumberOfMovie() {
        return this.numDirectedMovie;
    }

    public Date getDoB() {
        return this.dob;
    }

    public void setDoB(Date dob) {
        this.dob = dob;
    }

    public void setNumberOfMovie(int num) {
        this.numDirectedMovie = num;
    }

    @Override
    public String toString() {
        return String.format(
                "Director [ name = %s, surname = %s, dob = %s, movies directed = %s ]",
                this.name,
                this.surname,
                this.dob,
                this.numDirectedMovie
        );
    }
}
