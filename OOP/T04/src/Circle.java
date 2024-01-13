public class Circle {
    private double radius;
    private String color;

    public Circle() {
        this.radius = 1.0;
        this.color = "blue";
    }

    public Circle(double r) {
        this.radius = r;
        this.color = "blue";
    }

    public Circle(double r, String color) {
        this.radius = r;
        this.color = color;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double newRadius) {
        this.radius = newRadius;
    }

    public String getColour() {
        return this.color;
    }

    public void setColour(String colour) {
        this.color = colour;
    }

    public double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public String toString() {
        return String.format(
            "Circle { Radius = %h}"
            , this.radius 
            );
    }
}
