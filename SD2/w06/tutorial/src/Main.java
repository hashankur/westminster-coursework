public class Main {
    public static void main(String[] args) {
        Module m = new Module("SD1", 45.45, 34.34);
        Student s1 = new Student("Alex", "w12345", m);
        System.out.println(s1.getName());
    }
}