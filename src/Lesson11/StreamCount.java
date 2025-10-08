import static java.lang.IO.println;

// 3 подхода для создания потока
void main() {
    // создание потока из обычного массива
    IntStream intStream = Arrays.stream(new int[] { 1, 2, 4, 5, 7 } );
    intStream.forEach(IO::println);
    // кол-во положительных чисел
    long count = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(x -> x > 0).count();
    println(count);
    // среднее арифметическое чисел < 2
    double average = IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5)
                     .filter(w -> w < 2)
                     .average().getAsDouble();
    IO.println(average);
    // города с кол-вом букв 6
    ArrayList<String> cities = new ArrayList<>();
    Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
    cities.stream() // получаем поток
            .filter(s->s.length()==6) // применяем фильтрацию по длине строки
            .forEach(IO::println); // выводим отфильтрованные строки на консоль
            // s -> println(s)
}