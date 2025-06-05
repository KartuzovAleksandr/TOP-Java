/*
Проверка логина и пароля
Создать статический метод который принимает на вход три параметра: login, password и confirmPassword.
Login должен содержать только латинские буквы, цифры и знак подчеркивания.
Длина login должна быть не меньше 5 символов. Если login не соответствует этим требованиям, необходимо выбросить WrongLoginException.
Длина password должна быть не меньше 8 символов.
Password должен содержать только латинские буквы (большие или маленькие), цифры и знак подчеркивания (обязательно 3 из этих 4 групп символов).
Также password и confirmPassword должны быть равны. Если password не соответствует этим требованиям, необходимо выбросить WrongPasswordException.
WrongPasswordException и WrongLoginException - пользовательские классы исключения с двумя конструкторами – один по умолчанию,
второй принимает сообщение исключения и передает его в конструктор класса Exception.
Обработка исключений проводится внутри метода.
Используем multi-catch block.
Метод возвращает true, если значения верны или false в другом случае.
*/
// Академия TOP
// Сысуев Андрей ШС П21
import java.util.regex.Pattern;

void main() {
    Scanner sc = new Scanner(System.in);
    print("Enter your login: ");
    String login = sc.nextLine();
    print("Enter your password: ");
    String password = sc.nextLine();
    print("Confirm password: ");
    String confirmPassword = sc.nextLine();
    if (validateCredentials(login, password, confirmPassword))
        println("Вы успешно авторизованы !");
        else
            println("Вы не прошли авторизацию !");
}

public static boolean validateCredentials(String login, String password, String confirmPassword) {
    try {
        if (login == null || !login.matches("^[a-zA-Z0-9_]{5,}$")) {
            throw new WrongLoginException("Неверный формат или длина логина");
        }

        if (password == null || password.length() < 8) {
            throw new WrongPasswordException("Пароль слишком короткий");
        }

        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Пароль содержит недопустимые символы");
        }

        int criteriaCount = 0;

        if (Pattern.compile("[a-z]").matcher(password).find()) criteriaCount++;
        if (Pattern.compile("[A-Z]").matcher(password).find()) criteriaCount++;
        if (Pattern.compile("\\d").matcher(password).find()) criteriaCount++;
        if (Pattern.compile("_").matcher(password).find()) criteriaCount++;

        if (criteriaCount < 3) {
            throw new WrongPasswordException("Пароль не соответствует требованиям сложности");
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }

        return true;
    } catch (WrongLoginException | WrongPasswordException e) {
        return false;
    }
}

public static class WrongLoginException extends Exception {
    public WrongLoginException() {}
    public WrongLoginException(String message) {
        super(message);
        println(message);
    }
}

public static class WrongPasswordException extends Exception {
    public WrongPasswordException() {}
    public WrongPasswordException(String message) {
        super(message);
        println(message);
    }
}