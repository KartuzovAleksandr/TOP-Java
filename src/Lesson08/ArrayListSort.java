package Lesson08;

import java.util.*;
class ArrayListSort {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc=new Scanner(System.in);
        System.out.print("ArrayList Size: ");
        int n = sc.nextInt();

        ArrayList al = new ArrayList();

        System.out.println("Before: ");
        for (int i = 0; i < n; i++)
            al.add(r.nextInt(100));
        System.out.println(al);

        System.out.println("After sort: ");
        Collections.sort(al);
        System.out.println(al);

        System.out.println("In reverse order: ");
        Collections.sort(al, Collections.reverseOrder());
        System.out.println(al);
    }
}