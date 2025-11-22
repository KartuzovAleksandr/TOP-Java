package Lesson13;

import java.util.concurrent.*;

// Класс для обработки и вывода результатов вычислений
public class ResultPrinter {
    // Вспомогательный метод для обработки и вывода результатов
    public static void processAndPrintResults(int x, Future<Integer> y, Future<Integer> z)
            throws ExecutionException, InterruptedException {
        int resultY = y.get();
        int resultZ = z.get();
        int sum = resultY + resultZ;
        System.out.printf("Для x = %d:%n", x);
        System.out.printf("f(%d) = %d * %d = %d%n", x, x, x, resultY);
        System.out.printf("g(%d) = %d + 10 = %d%n", x, x, resultZ);
        System.out.printf("Сумма: %d + %d = %d%n", resultY, resultZ, sum);
    }
}