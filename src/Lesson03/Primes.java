// Данчук Александра
// Академия TOP, П23

package Lesson03;

import java.util.ArrayList;
import java.util.Scanner;

public class Primes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число: ");

        int number = sc.nextInt();

        if (number < 2) {
            System.out.println("Число должно быть больше 2");
            return;
        }

        ArrayList<Integer> primes = findPrimes(number);

        System.out.println("Последовательность простых чисел: " + primes.toString());

    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> findPrimes(int number) {
        ArrayList<Integer> primes = new ArrayList<>();

        if (number < 2) return primes;

        for (int i = 2; i <= number; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        return primes;
    }
}