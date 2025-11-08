void main() {
    var product1 = new Product<Integer, Double>(2343534, 345.234);
    IO.println(product1);

    var product2 = new ProductRecord<String, Integer>("34534", 345345);
    IO.println(product2);

}

class Product<T, D extends Number> {
    private final T article;
    private final D price;

    public Product(T article, D price) {
        this.article = article;
        this.price = price;
    }

    public T article() {
        return this.article;
    }

    public D price() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s %s", article, price);
    }
}

record ProductRecord<T, D>(T article, D price) {}