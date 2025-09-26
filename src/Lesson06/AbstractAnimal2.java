//Макешин Илья
//Академия TOP
//гр. П23 Оренбург
package Lesson06;

interface Speakable {
    void speak();
}

abstract class Animal {
    abstract void move();
}

class Dog extends Animal implements Speakable {
    void move() {
        System.out.println("Собака бегает");
    }

    public void speak() {
        System.out.println("Гав");
    }
}

public class AbstractAnimal2 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.move();
        dog.speak();
    }
}