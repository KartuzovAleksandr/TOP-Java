void main() {
    Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 17", 104000), new Phone("Samsung A56", 39000),
            new Phone("IQOO Z10 Turbo", 30000));
    Stream<Phone> phStream = phoneStream;
    // телефоны с ценой < 40k
    // phoneStream.filter(p -> p.getPrice() < 40000).forEach(p -> IO.println(p.getName())); // для класса
    phoneStream.filter(p -> p.Price() < 40000).forEach(p -> IO.println(p.Name()));
    // второй раз поток phoneStream использовать нельзя !!!
    phStream
            .map(p-> "Название: " + p.Name() + " Цена: " + p.Price())
            .forEach(IO::println);
}

// immutable record
record Phone(String Name, int Price) {};

// в меню Code / Generate для конструктора и геттеров - сеттеров
//static class Phone {
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public Phone(String name, int price) {
//        this.name = name;
//        this.price = price;
//    }
//
//    private String name;
//    private int price;
//}