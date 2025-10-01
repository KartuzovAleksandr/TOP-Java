import static java.lang.IO.println;

public void main() {
    var numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    Optional<Integer> min = numbers.stream().min(Integer::compare);
    Optional<Integer> max = numbers.stream().max(Integer::compare);
    println("Min Max");
    println(min.get()); // 1
    println(max.get()); // 9
    println("Min Max");
    println(numbers.stream().min(Integer::compare).get()); // 1
    int m = numbers.stream().max(Integer::compare).get();
    println(m);
}