class Phone{
    private String name;
    private String company;
    private int price;

    public Phone(String name, String comp, int price) {
        this.name=name;
        this.company=comp;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getCompany() { return company; }
}

public void main() {
    Stream<Phone> phoneStream = Stream.of(new Phone("iPhone 17 Pro", "Apple", 1000),
            new Phone("Pixel 11", "Google", 500),
            new Phone("iPhone 17", "Apple",450),
            new Phone("Galaxy S25", "Samsung", 440),
            new Phone("Galaxy A36", "Samsung", 340));

    Map<String, List<Phone>> phonesByCompany = phoneStream.collect(
            Collectors.groupingBy(Phone::getCompany));

    for (Map.Entry<String, List<Phone>> item : phonesByCompany.entrySet()) {
        System.out.println(item.getKey());
        for (Phone phone : item.getValue()){
            System.out.println(phone.getName());
        }
        System.out.println();
    }
}