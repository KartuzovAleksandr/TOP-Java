enum DayTime{
    MORNING ("Доброе утро"),
    AFTERNOON("Добрый день"),
    EVENING("Добрый вечер"),
    NIGHT("Доброй ночи");
    private String message;
    DayTime(String message) {
        this.message = message;
    }
    public String getMessage(){ return message;}
}

enum Operation {
    SUM {
        int action(int x, int y){ return x + y;}
    },
    SUBTRACT {
        int action(int x, int y){ return x - y;}
    },
    MULTIPLY {
        int action(int x, int y){ return x * y;}
    };
    abstract int action(int x, int y);
}

void main() {
    Operation op = Operation.SUM;
    IO.println(op.action(10, 4));   // 14
    op = Operation.MULTIPLY;
    IO.println(op.action(6, 4));    // 24

    // можно использовать Daytime.values(), Daytime.ordinal()
    var current = DayTime.EVENING;
    IO.println(current.getMessage());  // Добрый вечер
}