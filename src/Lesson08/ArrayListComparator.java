package Lesson08;
import java.util.*;
// сначала будут нечетные числа, потом четные
class MyComp implements Comparator<Integer> {
    public int compare (Integer first, Integer second) {
        int x = first % 2;
        int y = second % 2;
        int z;
        if (x == 1) {
            z = -1; // признак нечетного числа
        }
        else z = 1; // иначе четное
        return (z);
    } }
class ArrayListComparator {
    public static void main(String[] args) {
        Random r=new Random();
        Scanner sc=new Scanner(System.in);
        System.out.print("ArrayList Size: ");
        int n=sc.nextInt();
        ArrayList<Integer> ts=new ArrayList<Integer>();

        System.out.println("Before: ");
        for (int i=0; i<n; i++)
            ts.add(r.nextInt(100));
        System.out.println(ts);

        // применяем свой компаратор
        System.out.println("After sort: ");
        MyComp cp=new MyComp();
        ts.sort(cp);
        System.out.println(ts);

        sc.close();
    }
}