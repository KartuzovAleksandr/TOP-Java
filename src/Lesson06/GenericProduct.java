record product<T, U>(T Article, U price) {

    public String getInfo() {
        return String.format("Товар [Артикул: %s (%s), Цена: %s (%s)]",
                Article, Article.getClass().getSimpleName(),
                price, price.getClass().getSimpleName());
    }

    public static product<Integer, Integer> createIntegerPrice(Integer Article, Integer price) {
        return new product<>(Article, price);
    }

    public static product<String, Double> createDoublePrice(String Article, Double price) {
        return new product<>(Article, price);
    }

    public static product<Integer, Double> createMixed(Integer Article, Double price) {
        return new product<>(Article, price);
    }
}

void main(){

    product<Integer, Integer> product1 = new product<>(12345, 1000);
    product<String, Double> product2 = new product<>("ABC-123", 999.99);
    product<Integer, Double> product3 = new product<>(67890, 1499.50);

    product<Integer, Integer> product4 = product.createIntegerPrice(11111, 500);
    product<String, Double> product5 = product.createDoublePrice("XYZ-999", 750.25);

    System.out.println(product1.getInfo());
    System.out.println(product2.getInfo());
    System.out.println(product3.getInfo());

    System.out.println("Product 2 article: " + product2.Article());
    System.out.println("Product 3 price: " + product3.price());

    System.out.println(product1);
    System.out.println(product2);

    product<Integer, Integer> product6 = new product<>(12345, 1000);
    System.out.println("Product1 equals product6: " + product1.equals(product6));
}