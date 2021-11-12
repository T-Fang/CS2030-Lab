import java.util.Scanner;

public class Main {
    static Circle createCircle(Point p, Point q, double r){
        var angle = p.angleTo(q) + Math.PI / 2;
        var temp = r * r - p.distanceTo(q) * p.distanceTo(q) / 4;
        if (temp < 0 || p.distanceTo(q) == 0)
            return null;
        else
            return Circle.getCircle(p.midPoint(q).moveTo(angle, Math.sqrt(temp)), r);
    }
    public static void main (String[] args){
        java.util.Scanner sc = new java.util.Scanner(System.in);

        double px = sc.nextDouble();
        double py = sc.nextDouble();
        double qx = sc.nextDouble();
        double qy = sc.nextDouble();
        double r = sc.nextDouble();

        var result = createCircle(new Point(px, py), new Point(qx, qy), r);
        if(result == null)
            System.out.println("No valid circle can be created");
        else
            System.out.println("Created: " + result);

    }
}
