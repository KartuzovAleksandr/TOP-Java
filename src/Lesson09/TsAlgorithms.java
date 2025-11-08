package Lesson09;

import java.util.*;

public class TsAlgorithms {
    public static void main(String[] args) {
        Random r=new Random();
        Scanner sc=new Scanner(System.in);
        System.out.print("Size (размер коллекции): ");
        int n=sc.nextInt();

        System.out.println("TreeSet (сортируется в момент заполнения): ");
        TreeSet<Integer> ts=new TreeSet<Integer>(); // параметризуем TreeSet
        for (int i=0; i<n; i++)
            while (!ts.add(r.nextInt(100))); // добавляем с проверкой, чтобы все элементы были уникальны
        System.out.println(ts);
        System.out.println("Minimum: "+ts.first()); // первый элемент
        System.out.println("Maximum: "+ts.last());  // последний элемент

        System.out.println("ArrayList (несортированный): ");
        ArrayList<Integer> al=new ArrayList<Integer>(); // параметризуем ArrayList
        for (int i=0; i<n; i++)
            al.add(r.nextInt(100));
        System.out.println(al);
        System.out.println("Minimum: "+ Collections.min(al));
        System.out.println("Maximum: "+ Collections.max(al));

        sc.close(); // закрываем Scanner - облегчаем работу сборщика мусора JRE
    }
}