// Данчук Александра
// Академия TOP
// гр. П23 Оренбург
void main() {
    Animal dog = new Dog(112);
    dog.whoami();
    ((Dog) dog).speak();

    Flyable bird = new Pigeon(1);
    ((Pigeon) bird).whoami();
    bird.fly();
}

abstract class Animal {
    String name;
    int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    abstract void whoami();
}

interface Flyable {
    void fly();
}

interface Speakable {
    void speak();
}

class Dog extends Animal implements Speakable {
    public Dog(int age) {
        super("собака", age);
    }

    @Override
    public void whoami() {
        println("я собака");
    }

    @Override
    public void speak() {
        println("гав");
    }
}

class Pigeon extends Animal implements Flyable {
    public Pigeon(int age) {
        super("голубь", age);
    }

    @Override
    public void whoami() {
        println("я голубь");
    }

    @Override
    public void fly() {
        println("*летает*");
    }
}