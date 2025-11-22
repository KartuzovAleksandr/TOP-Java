package Lesson13.Reactive_cells;

import java.util.ArrayList;
import java.util.List;

// Класс для ячейки, которая является издателем и подписчиком
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue;
        System.out.println(this.name + ":" + newValue);
        notifyAllSubscribers();
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}