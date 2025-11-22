// запустить 1000 потоков, увеличивающих одну общую переменную odd на 1
// а вторую even будем увеличивать на 1, если odd нечетная
// в результате в переменной odd будет значение 1000, even = 500
// реализация с помощью volatile и Atomic
import java.util.concurrent.atomic.AtomicInteger;
void main() throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            threads[i] = new Thread(counter::increment);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        IO.println("odd = " + counter.getOdd());
        IO.println("even = " + counter.getEven());
}
    public class Counter {
        private AtomicInteger odd = new AtomicInteger(0);
        private volatile int even = 0;

        public void increment() {
            odd.incrementAndGet();
            synchronized(this) {
                if (getOdd() % 2 != 0) {
                    even++;
                }
            }
        }
        public int getOdd() {
            return odd.get();
        }
        public int getEven() {
            return even;
        }
    }