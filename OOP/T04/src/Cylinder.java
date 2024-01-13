public class Cylinder extends Circle {
    private double height;

    public Cylinder() {
        this.height = 1.0;
    }

    public Cylinder(double height) {
        this.height = height;
    }
    
    public Cylinder(double height, double radius) {
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public double getVolume() {
        double volume = super.getArea() * this.height;
        return volume;
    }
}
