package Lesson01;

import java.util.Scanner;
public class Begin01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");
        int x = sc.nextInt();
        System.out.println("Число в квадрате " + x * x);
    }
}