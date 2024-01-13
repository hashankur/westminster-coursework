public class App {
    public static void main(String[] args) throws Exception {
        Cylinder c1 = new Cylinder();

        System.out.println(String.format(
                "Cylinder: radius = %s, height = %s, base area = %s, volume = %s",
                c1.getRadius(), c1.getHeight(), c1.getArea(), c1.getVolume()));

        Cylinder c2 = new Cylinder(5.0);

        System.out.println(String.format(
                "Cylinder: radius = %s, height = %s, base area = %s, volume = %s",
                c2.getRadius(), c2.getHeight(), c2.getArea(), c2.getVolume()));

        Cylinder c3 = new Cylinder(5.0, 10.0);

        System.out.println(String.format(
                "Cylinder: radius = %s, height = %s, base area = %s, volume = %s",
                c3.getRadius(), c3.getHeight(), c3.getArea(), c3.getVolume()));
    }
}
