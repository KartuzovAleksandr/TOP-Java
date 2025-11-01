// Академия TOP
// Демидов Максим ШС П21
package Lesson05;

import java.util.Scanner;

class WrongLoginException extends Exception {
    public WrongLoginException() {
        super();
    }

    public WrongLoginException(String message) {
        super(message);
    }
}

class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}

public class passwd {

    private static void validateLogin(String login) throws WrongLoginException {
        if (login == null || login.length() < 5) {
            throw new WrongLoginException("Login must be at least 5 characters long");
        }

        if (!login.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongLoginException("Login can only contain Latin letters, numbers and underscores");
        }
    }

    private static void validatePassword(String password, String confirmPassword) throws WrongPasswordException  {
        if (password == null || password.length() < 8) {
            throw new WrongPasswordException("Password must be at least 8 characters long");
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Password and confirm password do not match");
        }

        int groupsCount = 0;
        if (password.matches(".*[a-z].*")) groupsCount++; // строчные буквы
        if (password.matches(".*[A-Z].*")) groupsCount++; // заглавные буквы
        if (password.matches(".*[0-9].*")) groupsCount++; // цифры
        if (password.matches(".*_.*")) groupsCount++;     // подчеркивания

        if (groupsCount < 3) {
            throw new WrongPasswordException("Password must contain at least 3 of the following: lowercase letters, uppercase letters, numbers, underscores");
        }

        if (!password.matches("^[a-zA-Z0-9_]+$")) {
            throw new WrongPasswordException("Password can only contain Latin letters, numbers and underscores");
        }
    }

    public static boolean validate(String login, String password, String confirmPassword) {
        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);
            return true;
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter your login: ");
        String login = in.nextLine();
        System.out.print("Enter your password: ");
        String password = in.nextLine();
        System.out.print("Enter your confirm password: ");
        String confirmPassword = in.nextLine();

        System.out.println(validate(login, password, confirmPassword));
    }
}