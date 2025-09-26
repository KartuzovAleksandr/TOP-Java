package Lesson06;

public class AbstractInterface {
    public static void main(String[] args) {
        Employee p1 = new Employee("Вася", "Сбер");
        Employee p2 = new Employee("Петя", "ВТБ");
        p1.display();
        p2.display();
        p1.print();
    }
    interface Sotrudnik {
        public String Family = "Павлов";
        void print();
    }
    static abstract class Person {
        public String Name;
        Person(String name) {
            Name = name;
        }
        public abstract void display();
    }
    static class Employee extends Person implements Sotrudnik {
        public String Bank;
        Employee(String name, String company) {
            super(name);
            Bank = company;
        }
        @Override
        public void display() {
            System.out.println("Имя = " + Name + " Компания = " + Bank);
        }

        @Override
        public void print() {
            System.out.println("Имя = " + Name + " Фамилия = " + Family);
        }
    }
}