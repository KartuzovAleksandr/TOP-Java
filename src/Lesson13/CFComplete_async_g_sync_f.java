package Lesson13;

import java.util.concurrent.*;

/* Реализация класса CFComplete с использованием CompletableFuture
  для асинхронного вычисления g(x) и синхронного вычисления f(x) */

public class CFComplete_async_g_sync_f {
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
            // Создание CompletableFuture для g(x)
            CompletableFuture<Integer> b = new CompletableFuture<>();
            // Сохранение f(x) в Future для использования в ResultPrinter
            Future<Integer> yFuture = CompletableFuture.completedFuture(f(x));
            // Асинхронное вычисление g(x) с завершением CompletableFuture
            executorService.submit(() -> b.complete(g(x)));
            // Обработка и вывод результатов
            ResultPrinter.processAndPrintResults(x, yFuture, b);
        }
    }
}
