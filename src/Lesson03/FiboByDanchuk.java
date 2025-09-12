// Данчук Александра
// Академия TOP, П23
void main() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Введите количество чисел фибоначчи: ");
    int count = sc.nextInt();
    if (count <= 0) {
        System.out.print("Количество должно быть больше 0");
        return;
    }
    long[] fibonacci = new long[count + 1];
    fibonacci[0] = 0;
    fibonacci[1] = 1;
    for (int i = 2; i <= count; i++) {
        fibonacci[i] = fibonacci[i - 1] + fibonacci[i -2];
    }
    System.out.println("Последовательность фибоначчи: " + Arrays.toString(fibonacci));
}