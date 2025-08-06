package Lesson13;

import java.util.concurrent.*;
import java.util.function.IntConsumer;

// Класс для демонстрации реактивного API с обратными вызовами
public class CallbackStyleExample {
    // Вспомогательный класс для хранения результатов f и g
    static class Result {
        int left = 0;  // Результат f(x)
        int right = 0; // Результат g(x)
    }

    // Функция f: асинхронно вычисляет квадрат числа и передаёт результат в callback
    public static void f(int x, ExecutorService executor, IntConsumer dealWithResult) {
        executor.submit(() -> {
            int result = x * x;
            dealWithResult.accept(result);
        });
    }

    // Функция g: асинхронно вычисляет число + 10 и передаёт результат в callback
    public static void g(int x, ExecutorService executor, IntConsumer dealWithResult) {
        executor.submit(() -> {
            int result = x + 10;
            dealWithResult.accept(result);
        });
    }

    // Главный метод программы
    public static void main(String[] args) {
        // Инициализация входного значения
        int x = 1337;
        // Создание объекта для хранения результатов
        Result result = new Result();
        // Счётчик для ожидания завершения f и g
        CountDownLatch latch = new CountDownLatch(2);
        // Создание пула потоков с 2 потоками в блоке try-with-resources
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Сохранение результатов f и g в Future для использования в ResultPrinter
            Future<Integer> yFuture = executor.submit(() -> x * x);
            Future<Integer> zFuture = executor.submit(() -> x + 10);

            // Вызов f с обратным вызовом
            f(x, executor, (int y) -> {
                synchronized (result) {
                    result.left = y;
                }
                latch.countDown();
            });

            // Вызов g с обратным вызовом
            g(x, executor, (int z) -> {
                synchronized (result) {
                    result.right = z;
                }
                latch.countDown();
            });

            // Ожидание завершения обоих обратных вызовов
            try {
                latch.await();
                ResultPrinter.processAndPrintResults(x, yFuture, zFuture);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Error processing results", e);
            }
        }
    }
}