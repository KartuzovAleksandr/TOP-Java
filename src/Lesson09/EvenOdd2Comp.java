import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите размер коллекции: ");
    int size = scanner.nextInt();

    //если надо ограниченный диапазон
    //numbers.add(ThreadLocalRandom.current().nextInt(1, 51));

    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < size; i++) {
        numbers.add(ThreadLocalRandom.current().nextInt(1, 100));
    }

    System.out.println("Исходная коллекция: " + numbers);

    numbers.sort(
            Comparator.comparing((Integer n) -> n % 2 != 0)
                    .thenComparing((a, b) -> a % 2 == 0 ? a - b : b - a)
    );
    System.out.println(" Коллекция после сортировки");
    System.out.println(numbers); // [2, 4, 6, 8, 9, 5, 3, 1]
}