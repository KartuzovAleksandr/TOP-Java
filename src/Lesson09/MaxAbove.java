void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите количество чисел: ");
    int n = scanner.nextInt();

    List<Double> numbers = new ArrayList<>();
    System.out.println("Введите числа:");
    for (int i = 0; i < n; i++) {
        numbers.add(scanner.nextDouble());
    }

    double avg = numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    double maxPos = numbers.stream().mapToDouble(x -> x - avg).filter(d -> d > 0).max().orElse(0.0);
    double minNeg = numbers.stream().mapToDouble(x -> x - avg).filter(d -> d < 0).min().orElse(0.0);

    System.out.printf("Среднее: %.2f%n", avg);
    System.out.printf("Наибольшее положительное отклонение: %.2f%n", maxPos);
    System.out.printf("Наименьшее отрицательное отклонение: %.2f%n", minNeg);
}