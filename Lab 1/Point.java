class Point {

    private double x;
    private double y;

    Point(double x, double y) {
        this.x = x; this.y = y;
    }

    Point midPoint(Point p){
        return new Point((this.x + p.x) / 2, (this.y + p.y) / 2);
    }

    double angleTo(Point p){
        return Math.atan2(p.y - this.y, p.x - this.x);
    }

    double distanceTo(Point otherpoint) {
        double dispX = this.x - otherpoint.x;
        double dispY = this.y - otherpoint.y;
        return Math.sqrt(dispX * dispX + dispY * dispY);
    }

    Point moveTo(double angle, double distance){
        return new Point(this.x + distance * Math.cos(angle), this.y + distance * Math.sin(angle));
    }

    String print(){
        return "point (" + String.format("%.3f", this.x) + ", " + String.format("%.3f", this.y) + ")";
    }
    public String toString() {

        return this.print();
    }

}