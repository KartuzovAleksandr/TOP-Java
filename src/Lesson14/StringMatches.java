//Березка Алексей
//гр. БВ312
//Академия TOP

//дана строка - разбить на слова (искл знаки препинания), посчитать
// 1) сумму цифр
// 2) кол-во больших англ и русских букв
// 3) среднюю длину слов
import java.util.List;

void main() {
        // Генерация случайной строки
        String input = generateRandomString();
        System.out.println("Сгенерированная строка: " + input);

        // Разбиение строки на слова (исключая знаки препинания)
        List<String> cleanWords = Arrays.asList(input.split("[^\\p{L}]+"));

        int digitSum = 0;
        int uppercaseCount = 0;
        int totalLetters = 0;

        // Анализ строки
        for (char c : input.toCharArray()) {
            // Суммирование цифр
            if (Character.isDigit(c)) {
                digitSum += Character.getNumericValue(c);
            }
            // Подсчет заглавных букв (английских и русских)
            if (Character.isUpperCase(c)) {
                uppercaseCount++;
            }
        }

        // Расчет средней длины слов
        for (String word : cleanWords) {
            totalLetters += word.length();
        }
        double averageWordLength = cleanWords.isEmpty() ? 0 : (double) totalLetters / cleanWords.size();

        // Вывод результатов
        System.out.println("1. Сумма цифр: " + digitSum);
        System.out.println("2. Количество заглавных букв: " + uppercaseCount);
        System.out.println("3. Средняя длина слов: " + averageWordLength);
}
// Генерация случайной строки
String generateRandomString() {
    return "ABCDEF 12GHI, JKLMNOPQRSTU! -VWXYZab cdefghijk lmnopqr stuvw45xyzАБВГДЕ ЁЖЗИЙКЛМН";
}