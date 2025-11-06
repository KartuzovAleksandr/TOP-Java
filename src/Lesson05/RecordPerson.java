package Lesson05;

public class RecordPerson {
    public static void main (String[] args){
        Person tom = new Person("Tom",  36);

        System.out.println(tom.name());     // Tom
        System.out.println(tom.age());      // 36
        System.out.println(tom.hashCode());

        Person bob = new Person("Петя", 21);
        Person tomas = new Person("Вася", 36);
        System.out.println(tomas); // toString() даже не нужен
        System.out.println(tom.equals(bob));    // false
        System.out.println(tom.equals(tomas));  // true
    }
}
record Person(String name, int age) { }

//class Person {
//    private final String name;
//    private final int age;
//
//    Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    String name() { return name; }
//    int age() { return age; }
//
//    public boolean equals(Object o) {
//        if (!(o instanceof Person)) return false;
//        Person other = (Person) o;
//        return other.name == name && other.age == age;
//    }
//
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
//
//    public String toString() {
//        return String.format("Person[name=%s, age=%d]", name, age);
//    }
//}