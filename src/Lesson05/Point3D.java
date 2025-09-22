package Lesson05;

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

    // полиморфизм - Dynamic Method Dispatch
    // через VMT - виртуальная таблица методов
    double distance(int x, int y) {
        int dx = this.getX() - x;
        int dy = this.getY() - y;
        int dz = 0;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
