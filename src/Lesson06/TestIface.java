package Lesson06;

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
        Client c = new Client();
        c.callback(42);
    }
}