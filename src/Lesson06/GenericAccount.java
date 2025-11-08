package Lesson06;

public class GenericAccount {
    public static void main(String[] args) {
        Account<String, Integer> acc1 = new Account<>("2345", 5000);
        var acc3 = new Account<>("2345", 5000);
        String acc1Id = acc1.getId();
        System.out.println(acc1Id);

        Account<Integer, Double> acc2 = new Account<>(2345, 50.00);
        System.out.println(acc2.getId() + " : "  + acc2.getSum());
    }
}
class Account<T, S> {
    private T id;
    private S sum;

    Account(T id, S sum) {
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public S getSum() { return sum; }
    public void setSum(S sum) { this.sum = sum; }
}