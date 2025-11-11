package Lesson07;

interface Callback {
    void callback(int param);
}

class Client implements Callback {
    public void callback(int p) {
        System.out.println("callback called with " + p);
    }
}

public class TestIface {
    public static void main(String[] args) {
        Callback c = new Client();
        System.out.println(c);
        c.callback(42);
        Client cc = new Client();
        System.out.println(cc);
        cc.callback(42);
        // var ccc = new Callback();
    }
}