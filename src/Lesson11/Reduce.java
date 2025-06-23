public void main() {
    Stream<Integer> numbersStream = Stream.of(1,2,3,4,5);
    Optional<Integer> result = numbersStream.reduce((x,y)->x*y);
    println(result.get());
}