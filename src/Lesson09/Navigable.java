package Lesson09;

import java.util.*;
public class Navigable {

    public static void main(String[] args) {
        TreeSet<String> states = new TreeSet<>();
        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Spain");
        states.add("Great Britain");

        System.out.println(states.first()); // получим первый - самый меньший элемент
        System.out.println(states.last()); // получим последний - самый больший элемент
        // получим поднабор от одного элемента до другого
        SortedSet<String> set = states.subSet("Germany", "Italy");
        System.out.println(set);
        // элемент из набора, который больше текущего
        String greater = states.higher("Germany");
        // элемент из набора, который меньше текущего
        String lower = states.lower("Germany");
        // возвращаем набор в обратном порядке
        NavigableSet<String> navSet = states.descendingSet();
        // возвращаем набор в котором все элементы меньше текущего
        SortedSet<String> setLower=states.headSet("Germany");
        // возвращаем набор в котором все элементы больше текущего
        SortedSet<String> setGreater=states.tailSet("Germany");
        System.out.println(navSet);
        System.out.println(setLower);
        System.out.println(setGreater);
    }
}