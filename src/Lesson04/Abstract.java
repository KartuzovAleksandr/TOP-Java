// абстрактный класс фигуры
abstract static class Shape{
    int x; // x-координата точки
    int y; // y-координата точки
    Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void print() {
        IO.println("Геометрическая фигура"); }
}
// производный класс прямоугольника
class Rectangle extends Shape
{
    int width;
    int height;
    // конструктор с обращением к конструктору класса Figure
    Rectangle(int x, int y, int width, int height) {
        super(x,y);
        this.width = width;
        this.height = height;
    }
    @Override
    void print() {
        System.out.printf("Пpямоугольник. Верхний левый угол: (%d, %d); Ширина: %d; Длина: %d\n",
                x,
                y,
                width,
                height);
    }
}
// производный класс круга
class Circle extends Shape
{
    int radius;
    // конструктор с обращением к конструктору класса Shape
    Circle(int x, int y, int radius){
        super(x,y);
        this.radius = radius;
    }
    @Override
    void print() {
        System.out.printf("Круг. Центр: (%d, %d); Радиус: %d\n", x, y, radius);
    }
}

void main() {
    Shape shape1 = new Rectangle(10, 15, 200, 150);
    Shape shape2 = new Circle(50, 60, 200);

    printShape(shape1);
    printShape(shape2);
}

static void printShape(Shape shape){
    shape.print();
}