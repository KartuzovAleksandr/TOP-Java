package Lesson09;
import java.util.*;

public class ArrayListEvenOdd {
        public static void main(String args[]) {
            Random r = new Random();
            Scanner sc = new Scanner(System.in);
            System.out.print("ArrayList Size (Размер массива): ");
            int n = sc.nextInt();
            ArrayList<Integer> al = new ArrayList<>(); // параметризуем ArrayList
            // заполняем динамический массив случайными числами
            System.out.println("Before (Исходный): ");
            for (int i = 0; i < n; i++)
                al.add(r.nextInt(100));
            System.out.println(al);
            // создаем копии исходной коллекции для нечетных и четных чисел
            ArrayList<Integer> al1 = new ArrayList<Integer>(al);
            ArrayList<Integer> al2 = new ArrayList<Integer>(al);
            // удаляем из коллекции al1 четные числа
            al1.removeIf(x -> x % 2 == 0);
            System.out.println("Odd numbers (Нечетные числа): ");
            System.out.println(al1);
            // удаляем из коллекции al2 нечетные числа
            al2.removeIf(x -> x % 2 != 0);
            System.out.println("Even numbers (Четные числа): ");
            System.out.println(al2);

            System.out.println("Odd numbers (sort increasing) Нечетные числа по возрастанию: ");
            Collections.sort(al1);
            System.out.println(al1);

            System.out.println("Even numbers (sort decreasing) Четные числа по убыванию: ");
            al2.sort(Collections.reverseOrder());
            System.out.println(al2);

            sc.close(); // закрываем класс Scanner - облегчаем работу сборщику мусора JRE
    }
}