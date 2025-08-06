package Lesson13;

import java.util.concurrent.*;

// Класс для демонстрации комбинирования CompletableFuture с thenCombine
public class CFCombine {
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
            // Создание CompletableFuture для f(x) и g(x)
            CompletableFuture<Integer> a = new CompletableFuture<>();
            CompletableFuture<Integer> b = new CompletableFuture<>();
            // Асинхронное вычисление f(x) и g(x)
            executorService.submit(() -> a.complete(f(x)));
            executorService.submit(() -> b.complete(g(x)));
            // Обработка и вывод результатов
            ResultPrinter.processAndPrintResults(x, a, b);
        }
    }
}