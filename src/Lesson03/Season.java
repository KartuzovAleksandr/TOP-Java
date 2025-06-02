import java.util.Scanner;
void main() {
    print("Введите месяц: ");
    Scanner sc = new Scanner(System.in);
    int month = sc.nextInt();
    String season;
    switch (month) {
        case 12, 1, 2 -> season = "Winter";
        case 3, 4, 5 -> season = "Spring";
        case 6, 7, 8 -> season = "Summer";
        case 9, 10, 11 -> season = "Autumn";
        default -> season = "Bogus Month";
    }
    println("Время года - " + season);
}
