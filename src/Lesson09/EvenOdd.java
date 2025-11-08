package Lesson09;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class EvenOdd {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите размер коллекции: ");
            int size = scanner.nextInt();

            ArrayList<Integer> numbers = new ArrayList<>();
            Random random = new Random();

            // Заполнение коллекции случайными числами
            for (int i = 0; i < size; i++) {
                numbers.add(random.nextInt(100)); // Случайные числа от 0 до 99
            }

            System.out.println("Сгенерированные числа: " + numbers);

            // Создание списков для четных и нечетных чисел
            List<Integer> evenNumbers = new ArrayList<>(numbers);
            List<Integer> oddNumbers = new ArrayList<>(numbers);

            // Удаление нечетных чисел из evenNumbers и четных из oddNumbers
            evenNumbers.removeIf(n -> n % 2 != 0);
            oddNumbers.removeIf(n -> n % 2 == 0);

            // Сортировка четных по возрастанию
            Collections.sort(evenNumbers);
            // Сортировка нечетных по убыванию
            oddNumbers.sort(Comparator.reverseOrder());

            // Очистка исходного списка и добавление отсортированных чисел
            numbers.clear();
            numbers.addAll(evenNumbers);
            numbers.addAll(oddNumbers);

            System.out.println("Результат (четные по возрастанию, нечетные по убыванию): " + numbers);

            scanner.close();
        }
    }