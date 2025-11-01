package Lesson04;

class Point {
    // not inheritance
//    final class Point {
    private int x, y;

    // auto-generated
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y) {
        // private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        // x = -1; y = -1;
        this(-1,-1);
    }
    // not to override
//    final double distance(int x, int y) {
    double distance(int x, int y) {
        int dx = this.x - x;
        int dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    double distance(Point p) {
        return distance(p.x, p.y);
    }
}