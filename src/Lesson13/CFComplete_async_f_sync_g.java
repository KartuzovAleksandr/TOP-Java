package Lesson13;


/* Реализация класса CFComplete с использованием CompletableFuture
  для асинхронного вычисления f(x) и синхронного вычисления g(x) */

import java.util.concurrent.*;

// Класс для демонстрации использования CompletableFuture с методом complete
public class CFComplete_async_f_sync_g {
    // Функция f: возвращает квадрат входного числа
    public static int f(int x) {
        return x * x;
    }

    // Функция g: возвращает число, увеличенное на 10
    public static int g(int x) {
        return x + 10;
    }

    // Главный метод программы
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        // Инициализация входного значения
        int x = 1337;
        // Создание пула потоков с 2 потоками в блоке try-with-resources
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            // Создание CompletableFuture для f(x)
            CompletableFuture<Integer> a = new CompletableFuture<>();
            // Сохранение g(x) в Future для использования в ResultPrinter
            Future<Integer> zFuture = CompletableFuture.completedFuture(g(x));
            // Асинхронное вычисление f(x) с завершением CompletableFuture
            executorService.submit(() -> a.complete(f(x)));
            // Обработка и вывод результатов
            ResultPrinter.processAndPrintResults(x, a, zFuture);
        }
    }
}