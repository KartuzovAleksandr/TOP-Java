package Lesson05;
import static java.lang.System.out;

class Point3D extends Point {
    int z;

    Point3D(int x, int y, int z) {
//        this.x = x;
//        this.y = y;
        super(x, y);
        this.z = z;
    }

    Point3D() {
        this(-1, -1, -1);
    }

    double distance(int x, int y, int z) {
        int dx = this.getX() - x;
        int dy = this.getY() - y;
        int dz = this.z - z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    double distance(Point3D other) {
        return distance(other.getX(), other.getY(), other.z);
    }

    double distance(int x, int y) {
        int dx = this.getX() - x;
        int dy = this.getY() - y;
        int dz = 0;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}

public class Points {
    public static void main(String[] args) {
        Point p1 = new Point(10,20);
        Point p2 = new Point();
        out.println("x = " + p1.getX() + " у = " + p1.getY());
        System.out.println("x = " + p2.getX() + " у = " + p2.getY());
        System.out.println(p1.distance(p2));
        System.out.println(p1.distance(20, 50));
        // Point3D p3 = new Point3D(10, 20, 30);
        var p3 = new Point3D(10, 20, 30);
        // in C# 12 (.net 8)
        // Point3D p3 = new(10, 20, 30);
        System.out.println(p3.distance(20, 50)); // полиморфизм - overriding
        System.out.println(p3.distance(20, 50, 100));
    }
}