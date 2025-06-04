void main() {
    Scanner sc = new Scanner(System.in);
    print("Enter your login: ");
    String login = sc.nextLine();
    print("Enter your password: ");
    String password = sc.nextLine();
    print("Confirm password: ");
    String confirmPassword = sc.nextLine();
    println(CheckPassword(login, password, confirmPassword));
}
boolean CheckPassword(String login, String password, String confirmPassword) {

    return password.equals(confirmPassword);
}