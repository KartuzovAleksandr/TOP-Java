// абстрактный класс фигуры
abstract class Shape {
    int x; // x-координата точки
    int y; // y-координата точки
    Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }
    abstract void print();
    // абстрактный метод для вычисления периметра
    abstract double getPerimeter();
    // абстрактный метод для вычисления площади
    abstract double getArea();
//    void print() {
//        IO.println("Геометрическая фигура"); }
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

    @Override
    double getPerimeter() {
        return this.width * 2 + this.height * 2;
    }

    @Override
    double getArea() {
        return this.width * this.height;
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

    @Override
    double getPerimeter() {
        return this.radius * Math.PI * 2;
    }

    @Override
    double getArea() {
        return this.radius * Math.PI;
    }
}

void main() {
    Shape shape1 = new Rectangle(10, 15, 200, 150);
    printShape(shape1);
    IO.println("Площадь прямоугольника: " + shape1.getArea());
    IO.println("Периметр прямоугольника: " + shape1.getPerimeter());

    Shape shape2 = new Circle(50, 60, 200);
    printShape(shape2);
    IO.println("Площадь круга: " + shape2.getArea());
    IO.println("Периметр круга: " + shape2.getPerimeter());
}

static void printShape(Shape shape){
    shape.print();
}