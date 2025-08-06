package Lesson13;

import java.util.Random;

// Класс, моделирующий интернет-магазин с синхронным методом получения цены товара
public class Shop_sync {
    // Генератор случайных чисел для имитации цены
    private static final Random random = new Random();

    // Метод для имитации секундной задержки
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для получения цены товара по его названию
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    // Вспомогательный метод для вычисления цены с задержкой
    private double calculatePrice(String product) {
        delay();
        // Генерация случайной цены на основе первых двух символов названия
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // Главный метод для демонстрации работы
    public static void main(String[] args) {
        Shop_sync shop = new Shop_sync();
        String product = "iPhone13";
        // Получение цены товара
        double price = shop.getPrice(product);
        // Вывод результата
        System.out.printf("Цена товара '%s': %.2f%n", product, price);
    }
}