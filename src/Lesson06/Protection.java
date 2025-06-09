package Lesson06;

public class Protection {
    int n = 1;
    private int n_pri = 2;
    protected int n_pro = 3;
    public int n_pub = 5;
    public Protection() {
        System.out.println("base constructor");
        System.out.println("n = " + n);
        System.out.println("n_pri = " + n_pri);
        System.out.println("n_pro = "	+ n_pro);
        System.out.println("n_pub = " + n_pub);
    }
}
class Derived extends Protection {
    Derived() {
        System.out.println("derived constructor");
        System.out.println("n = " + n);
        // только в классе
        // System.out.println("n_pri = " + n_pri);
        System.out.println("n_pro = "	+ n_pro);
        System.out.println("n_pub = "	+ n_pub);
    }
}
class SamePackage {
    SamePackage() {
        Protection p = new Protection();
        System.out.println("same package constructor");
        System.out.println("n = " + p.n);
// только в классе
// System.out.println("n_pri = " + p.n_pri);
        System.out.println("n_pro = " + p.n_pro);
// только в классе и подклассе
// System.out.println("n_pripro = " + p.n_pripro):
        System.out.println("n_pub = " + p.n_pub);
    } }