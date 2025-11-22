package Lesson13.Reactive_cells;

// Класс для ячейки, которая суммирует значения двух входных ячеек
public class ArithmeticCell extends SimpleCell {
    private int left;
    private int right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(left + this.right); // Обновляем значение и уведомляем подписчиков
    }

    public void setRight(int right) {
        this.right = right;
        onNext(right + this.left); // Обновляем значение и уведомляем подписчиков
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
