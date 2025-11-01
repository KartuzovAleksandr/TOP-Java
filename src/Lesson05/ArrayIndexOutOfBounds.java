import static java.lang.IO.println;

void main() {
    try {
        int[] numbers = new int[3];
        numbers[4] = 45;
        println(numbers[4]);
    }
    catch (ArrayIndexOutOfBoundsException ex) {
        println("Возникла ошибка выхода за границы массива");
        // ex.printStackTrace();
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }
    println("Программа завершена");

    int d = 0;
    int a = 42 / d;
}