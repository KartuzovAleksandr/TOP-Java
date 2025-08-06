package Lesson13;

import java.util.concurrent.*;

// Класс для демонстрации многопоточной обработки с ExecutorService
public class ExecutorServiceExample {
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
            // Запуск задачи для вычисления f(x) в отдельном потоке
            Future<Integer> y = executorService.submit(() -> f(x));
            // Запуск задачи для вычисления g(x) в отдельном потоке
            Future<Integer> z = executorService.submit(() -> g(x));
            // Обработка и вывод результатов
            ResultPrinter.processAndPrintResults(x, y, z);
        }
    }
}