package Lesson10;

public class LambdaT {
    public static void main(String[] args) {
        Operation<Integer> operation1 = (x, y)-> x + y;
        Operation<String> operation2 = (x, y) -> x + y;

        System.out.println(operation1.calculate(20, 10)); //30
        System.out.println(operation2.calculate("20", "10")); //2010
    }
}
interface Operation<T>{
    T calculate(T x, T y);
}