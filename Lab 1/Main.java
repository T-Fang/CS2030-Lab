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

        int n = sc.nextInt();
        Point[] pointSet = new Point[n];
        for(int i = 0; i < n; i++){
            pointSet[i] = new Point(sc.nextDouble(), sc.nextDouble());     
        }
//        for(int i = 0; i < n; i++){
//            System.out.println(pointSet[i]);
//        }
        int maxDiscCov = 0;
        Circle temp;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n ; j++){
//                System.out.println("Current Points: " + pointSet[i] + pointSet[j]);
//                System.out.println("Current Maximum Disc Coverage: " + maxDiscCov);
                temp = Main.createCircle(pointSet[i], pointSet[j], 1);
                if(temp != null) {
                    for (int k = 0; k < n; k++) {
                        if (temp.contains(pointSet[k])) count++;
                    }

                    if (count > maxDiscCov) maxDiscCov = count;
                    count = 0;
                }
            }
        }
        System.out.println("Maximum Disc Coverage: " + maxDiscCov);
    }
}
