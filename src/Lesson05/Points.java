package Lesson05;
import static java.lang.System.out;

public class Points {
    public static void main(String[] args) {
        Point p1 = new Point(10,20);
        Point p2 = new Point();
        out.println("x = " + p1.getX() + " у = " + p1.getY());
        System.out.println("x = " + p2.getX() + " у = " + p2.getY());
        System.out.println(p1.distance(p2));
        System.out.println(p1.distance(20, 50));
        Point3D p3 = new Point3D(10, 20, 30);
        // var p3 = new Point3D(10, 20, 30);
        // in C# 12 (.net 8)
        // Point3D p3 = new(10, 20, 30);
        System.out.println(p3.distance(20, 50)); // полиморфизм - overriding
        System.out.println(p3.distance(20, 50, 100));
    }
}