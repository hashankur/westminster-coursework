public class Book implements Comparable{
    private String title;
    private double price;
    private int year;
    private String author;

    public Book(String title, double price, int year, String author) {
        this.title = title;
        this.price = price;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        }
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author + " published in " + year + " for $" + price;
    }

    @Override
    public int compareTo(Object o) {
        Book b = (Book) o;
        return this.year - b.year;
    }
}
