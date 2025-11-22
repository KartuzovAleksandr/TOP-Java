package Lesson13.Reactive_cells;

import java.util.concurrent.*;

// Класс для демонстрации реактивной модели с ячейками
public class ReactiveCells {
    // Главный метод программы
    public static void main(String[] args) {
        // Инициализация входных значений
        int x = 1337; // Для C1
        int y = 20;   // Для C2
        int z = 1;    // Для C4

        // Создание ячеек
        ArithmeticCell c5 = new ArithmeticCell("C5");
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c4 = new SimpleCell("C4");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        // Настройка подписок
        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);
        c3.subscribe(c5::setLeft);
        c4.subscribe(c5::setRight);

        // Создание пула потоков с 2 потоками в блоке try-with-resources
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Асинхронное обновление значений ячеек
            System.out.printf("Для x = %d, y = %d:%n", x, y);
            executor.submit(() -> c1.onNext(x)); // C1 = 1337
            executor.submit(() -> c2.onNext(y)); // C2 = 20
            executor.submit(() -> c4.onNext(z)); // C4 = 1

            // Ожидание завершения всех задач
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Error waiting for executor shutdown", e);
            }

            // Вывод суммы C3 (аналог f(x) + g(y))
            System.out.printf("Сумма: %d + %d = %d%n", c3.getLeft(), c3.getRight(), c3.getValue());
        }
    }
}