@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
public void main() {
        // Используем лямбду
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        println(add.calculate(2, 3));     // 5
        println(multiply.calculate(2, 3)); // 6
}