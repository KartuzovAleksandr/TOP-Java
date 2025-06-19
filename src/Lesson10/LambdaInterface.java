package Lesson10;

public class LambdaInterface {
    public static void main(String[] args) {
        Operationable operation;
        operation = (x,y) -> x + y; // Integer::sum
        Operationable op_mul = (x, y) -> x * y;
//        operation = (int x, int y) -> x + y;
//        Operationable operation = new Operationable(){
//
//            public int calculate(int x, int y){
//
//                return x + y;
//            }
//        };
        int result = operation.calculate(10, 20);
        System.out.println(result); // 30
        result = op_mul.calculate(10, 20);
        System.out.println(result); // 30
    }
}
interface Operationable{
    int calculate(int x, int y);
}