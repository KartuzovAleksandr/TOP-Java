void main() {
    SecureRandom r = new SecureRandom();
    Scanner sc = new Scanner(System.in);
    print("Введите размер массива: ");
    int n = sc.nextInt();
    Integer[] m = new Integer[n];

    println("Before: ");
    for (int i = 0; i < n; i++) {
        m[i] = r.nextInt(100);
        print(m[i] + " ");
    }
    System.out.println();

    System.out.println("After Arrays.sort: ");
    // встроенный метод сортировки массива
    // Dual-Pivot Quicksort
    // в обратном порядке
    Arrays.sort(m, Collections.reverseOrder());
    // у каждого класса есть метод преобразования в строку
    println(Arrays.toString(m));
}