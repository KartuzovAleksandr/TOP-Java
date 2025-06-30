import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

    public static LocalDate getSysAdminDay(int year) {
        LocalDate juleFirst = LocalDate.of(year, 7, 1);
        return juleFirst.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));
    }

    public void main() {
        int currentYear = LocalDate.now().getYear();
        // Форматтер с русской локалью
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));

        System.out.println("День сисадмина в ближайшие 10 лет:");
        for (int i = 0; i < 10; i++) {
            LocalDate sysAdminDay = getSysAdminDay(currentYear + i);
            System.out.println(sysAdminDay.format(formatter));
        }
    }