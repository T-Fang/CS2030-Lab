public class Circle {
    Point center;
    double radius;

    private Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    static Circle getCircle(Point centre, double radius) {
        if (radius > 0)
            return new Circle(centre, radius);
        else
            return null;
    }

    boolean contains(Point point) {
        return center.distanceTo(point) <= radius;
    }

    public String toString() {
        return "circle of radius " + String.format("%.1f", radius) + " centered at " + center.print();
    }


}
