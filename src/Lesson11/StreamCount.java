import static java.lang.IO.println;

public void main() {
    long count = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(w -> w > 0).count();
    double average = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)
                     .filter(w -> w < 2)
                     .average().getAsDouble();
    println(count);
    println(average);
}