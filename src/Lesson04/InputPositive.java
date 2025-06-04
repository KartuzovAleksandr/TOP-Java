// Академия TOP
// Сысуев Андрей гр. ШС П21
void main() {
    Scanner in = new Scanner(System.in);
    int n;
    while (true) {
        System.out.print("Введите целое положительное число: ");
        try {
            n = Integer.parseInt(in.nextLine());
            if (n <= 0) {
                throw new Exception();
            }
            break;
        } catch (Exception e) {
            System.out.println("Ошибка! Введите целое положительное число!");
        }
    }
    System.out.println("Молодец!");
}