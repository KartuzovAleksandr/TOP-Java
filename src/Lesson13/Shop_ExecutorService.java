package Lesson13;

import java.util.Random;
import java.util.concurrent.*;

// Класс для демонстрации асинхронного получения цены в интернет-магазине
public class Shop_ExecutorService {
    // Главный метод программы
    public static void main(String[] args) {
        AsyncShop shop = new AsyncShop("ЛучшийМагазин");
        String product = "iPhone13";

        // Создание пула потоков с 2 потоками в блоке try-with-resources
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Замер времени начала запроса
            long start = System.nanoTime();
            // Асинхронный запрос цены товара
            Future<Double> futurePrice = shop.getPriceAsync(product, executor);
            // Время получения Future (не самого результата)
            long invocationTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            System.out.println("Запрос возвращён через " + invocationTime + " мс");

            // Выполнение других задач параллельно
            doSomethingElse();

            try {
                // Получение результата (блокирующий вызов)
                double price = futurePrice.get();
                System.out.printf("Цена: %.2f%n", price);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при получении цены", e);
            }

            // Общее время выполнения
            long retrievalTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            System.out.println("Цена получена через " + retrievalTime + " мс");

            // Завершение работы пула потоков
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Ошибка при завершении работы пула потоков", e);
            }
        }
    }

    // Метод для имитации параллельных задач
    private static void doSomethingElse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Выполняется другая задача...");
    }
}

// Класс, моделирующий интернет-магазин
record AsyncShop(String name) {
    private static final Random random = new Random();

    // Конструктор магазина

    // Метод для имитации секундной задержки
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Синхронный метод для получения цены товара
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    // Асинхронный метод для получения цены товара
    public Future<Double> getPriceAsync(String product, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product), executor);
    }

    // Вспомогательный метод для вычисления цены с задержкой
    private double calculatePrice(String product) {
        delay();
        // Генерация случайной цены на основе первых двух символов названия
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}