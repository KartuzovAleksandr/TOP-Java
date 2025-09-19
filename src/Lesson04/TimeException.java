//Данчук Александра
//Академия TOP
//гр. П23
package Lesson04;

import java.util.Scanner;

public class TimeException {
    public static void main(String[] args) {
    var scanner = new Scanner(System.in);
        System.out.print("Введите время (чч:мм:сс): ");
    String timeString = scanner.next();

        try {
        String[] time = timeString.split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        int secs = Integer.parseInt(time[2]);

        if (hour < 0 || hour > 23) {
            throw new WrongHourException("0 < час < 23");
        }

        if (minute < 0 || minute > 59) {
            throw new WrongMinuteException("0 < минуты < 60");
        }

        if (secs < 0 || secs > 59) {
            throw new WrongSecsException("0 < секунды < 60");
        }

        System.out.printf("Время прошло валидацию, часы: %s, минуты: %s, секунды: %s", hour, minute, secs);
    }
        catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Вы указали время в неверном формате");
    }
        catch (NumberFormatException e) {
        System.out.println("Время должно содержать только цифры");
    }
        catch (WrongHourException | WrongMinuteException | WrongSecsException e) {
        System.out.println(e.getMessage());
    }
        catch (Exception e) {
        System.out.println("Неизвестная ошибка");
    }
}
}

class WrongHourException extends Exception {
    public WrongHourException() {
        super();
    }

    public WrongHourException(String message) {
        super(message);
    }
}

class WrongMinuteException extends Exception {
    public WrongMinuteException() {
        super();
    }

    public WrongMinuteException(String message) {
        super(message);
    }
}

class WrongSecsException extends Exception {
    public WrongSecsException() {
        super();
    }

    public WrongSecsException(String message) {
        super(message);
    }
}