package Lesson06;

public class Protection {
    int n = 1;
    private int n_private = 2;
    protected int n_protected = 3;
    // удалили из Java
    // private protected int n_pripro = 4;
    public int n_public = 5;
    public Protection() {
        System.out.println("base constructor");
        System.out.println("n = " + n);
        System.out.println("n_pri = " + n_private);
        System.out.println("n_pro = "	+ n_protected);
        System.out.println("n_pub = " + n_public);
    }
}
class Derived extends Protection {
    Derived() {
        System.out.println("derived constructor");
        System.out.println("n = " + n);
        // только в классе
        // System.out.println("n_pri = " + n_private);
        System.out.println("n_pro = "	+ n_protected);
        System.out.println("n_pub = "	+ n_public);
    }
}
class SamePackage {
    SamePackage() {
        Protection p = new Protection();
        System.out.println("same package constructor");
        System.out.println("n = " + p.n);
        // только в классе
        // System.out.println("n_private = " + p.n_private);
        System.out.println("n_pro = " + p.n_protected);
        // только в классе и подклассе
        // System.out.println("n_pripro = " + p.n_pripro):
        System.out.println("n_pub = " + p.n_public);
    } }