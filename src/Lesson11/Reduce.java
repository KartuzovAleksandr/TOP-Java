public void main() {
    Stream<Integer> numbersStream = Stream.of(1,2,3,4,5);
    Optional<Integer> result = numbersStream.reduce((x,y) -> x * y);
    // проверка значения, чтобы не было java.util.NoSuchElementException
//    if (result.isPresent()) {
//        println(result.get()); // так тоже можно, но вдруг поток пустой
//    }
    result.ifPresent(IO::println);

    // проверка и вывод ошибки
    ArrayList<Integer> numbers = new ArrayList<>();
    Optional<Integer> min = numbers.stream().min(Integer::compare);
    min.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("Value not found")
    );
}