@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
public void main() {
        // Используем лямбду
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        System.out.println(add.calculate(2, 3));     // 5
        System.out.println(multiply.calculate(2, 3)); // 6
}