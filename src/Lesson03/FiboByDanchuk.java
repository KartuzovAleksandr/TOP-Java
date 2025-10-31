// Данчук Александра
// Академия TOP, П23
void main() {
    Scanner sc = new Scanner(System.in);
    IO.print("Введите количество чисел Фибоначчи: ");
    int count = sc.nextInt();
    if (count <= 0) {
        IO.print("Количество должно быть больше 0");
        return;
    }
    long[] fibonacci = new long[count + 1];
    fibonacci[0] = 0;
    fibonacci[1] = 1;
    for (int i = 2; i <= count; i++) {
        fibonacci[i] = fibonacci[i - 1] + fibonacci[i -2];
    }
    IO.println("Последовательность Фибоначчи: " + Arrays.toString(fibonacci));
}