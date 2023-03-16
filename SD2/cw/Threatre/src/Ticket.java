public class Ticket {
    private int row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(int row, int seat, int price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public void print() {
        person.getDetails();
        System.out.println("Row: " + row + ", Seat: " + seat);
        System.out.println("Price: £" + price);
    }

    public int getPrice() {
        return price;
    }

    public boolean isEqual(int row, int seat) {
        return this.row == row && this.seat == seat;
    }
}
