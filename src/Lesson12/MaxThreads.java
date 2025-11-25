void main() {
    var threadCount = new AtomicInteger(0);
    try {
        while (true) {
            var thread = new Thread(() -> {
                threadCount.incrementAndGet();
                LockSupport.park();
            });
            thread.start();
        }
    } catch (OutOfMemoryError error) {
        System.out.println("Лимит ваших потоков: " + threadCount);
    }
}