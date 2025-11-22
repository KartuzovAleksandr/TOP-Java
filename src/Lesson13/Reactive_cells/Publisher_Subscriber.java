package Lesson13.Reactive_cells;

// Интерфейс издателя для подписки на события
interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}

// Интерфейс подписчика для обработки событий
interface Subscriber<T> {
    void onNext(T t);
}