@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
// делаем реализацию ФИ с помощью лямбды (анонимный метод)
void main() {
        // Используем лямбду
        Calculator add = (a, b) -> a + b; // Integer::sum
        Calculator multiply = (a, b) -> a * b;
        System.out.println(add.calculate(2, 3));     // 5
        System.out.println(multiply.calculate(2, 3)); // 6
}

// полное определение
//void main(String[] args) {
//    Operation op = Operations::add;
//    System.out.println(op.execute(5, 4));       // 9
//    op = Operations::mul;
//    System.out.println(op.execute(5, 4));       // 20
//}
//interface Operation{
//    int execute(int x, int y);
//}
//class Operations{
//    static int add(int a, int b){ return a + b; }
//    static int mul(int a, int b){ return a * b; }
//}

// передача ссылки через параметр
//void main(String[] args) {
//    doOperation(10, 4, Operations::add);  // 14
//    doOperation(10, 4, Operations::mul);  // 40
//}
//
//static void doOperation(int a, int b, Operation op)
//{
//    System.out.println(op.execute(a, b));
//}
//}
//interface Operation{
//    int execute(int x, int y);
//}
//class Operations{
//    static int add(int a, int b){ return a + b; }
//    static int mul(int a, int b){ return a * b; }
//}