import static java.lang.IO.print;
import static java.lang.IO.println;

// Академия TOP
// Малютина Анастасия Анатольевна гр. ШС П21
void main() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        try {
            print("Введите целое положительное число (>0): ");
            String input = scanner.nextLine();
            int number = Integer.parseInt(input);
            if (number <= 0) {
                throw new IllegalArgumentException("Число должно быть больше 0.");
            }
            println("Вы ввели: " + number);
            break;
        }
        catch (NumberFormatException e) {
            println("Ошибка: Введено некорректное число. Пожалуйста, введите целое число.");
        }
        catch (IllegalArgumentException e) {
            println("Ошибка: " + e.getMessage());
        }
        catch (Exception e) {
            println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}