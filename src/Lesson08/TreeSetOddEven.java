package Lesson08;
import java.util.*;
public class TreeSetOddEven {
        public static void main(String[] args) {
            Random r=new Random();
            Scanner sc=new Scanner(System.in);
            System.out.print("TreeSet Size: ");
            int n=sc.nextInt();

            System.out.println("Before: ");
            TreeSet<Integer> ts=new TreeSet<Integer>();
            for (int i=0; i<n; i++)
                while (!ts.add(r.nextInt(100)));
            System.out.println(ts);

            Iterator it;
            int x;
            it = ts.iterator();
            System.out.println("Odd numbers: ");
            while (it.hasNext()) {
                x = (int)it.next();
                if (x % 2 != 0)
                    System.out.print(x + " ");
            }
            System.out.println();

            it= ts.iterator();
            System.out.println("Even numbers: ");
            while (it.hasNext()) {
                x=(int)it.next();
                if (x % 2==0)
                    System.out.print(x+" ");
            }

            sc.close();
        }
    }