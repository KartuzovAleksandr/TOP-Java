public static LocalDate getElectionDay(int year) {
    // Получаем 1 ноября заданного года
    LocalDate novemberFirst = LocalDate.of(year, 11, 1);
    // Находим первый понедельник >= 1 ноября
    LocalDate firstMonday = novemberFirst.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    // День выборов — это следующий вторник после первого понедельника
    return firstMonday.plusDays(1); // Вторник = понедельник + 1 день
}

public void main() {
    // int currentYear = LocalDate.now().getYear();
    int currentYear = 2028;
    LocalDate electionDay = getElectionDay(currentYear);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    println("День выборов в " + currentYear + " году: " + electionDay.format(formatter));
    println("День недели: " + electionDay.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("ru")));
    // или по новому
    DateTimeFormatter ft = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
            .withLocale(Locale.forLanguageTag("ru"));
    println(electionDay.format(ft));
}