package Lesson13;

import java.util.concurrent.*;

// Класс для демонстрации фьючерсного API
public class FutureAPI {
    // Функция f: возвращает Future с квадратом входного числа
    public static Future<Integer> f(int x, ExecutorService executor) {
        return executor.submit(() -> x * x);
    }

    // Функция g: возвращает Future с числом, увеличенным на 10
    public static Future<Integer> g(int x, ExecutorService executor) {
        return executor.submit(() -> x + 10);
    }

    // Главный метод программы
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        // Инициализация входного значения
        int x = 1337;
        // Создание пула потоков с 2 потоками в блоке try-with-resources
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Вызов методов f и g, возвращающих Future
            Future<Integer> y = f(x, executor);
            Future<Integer> z = g(x, executor);
            // Обработка и вывод результатов
            ResultPrinter.processAndPrintResults(x, y, z);
        }
    }
}