import java.time.LocalDate;
import java.time.Period;

public class JavaLibraries {
    static void main()  {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate myBirthday = LocalDate.of(1983,3,10);
        Period difference = Period.between(yesterday, myBirthday);
        System.out.println("There are " + difference.getYears() + " years and " + difference.getMonths() + " Months and " + difference.getDays() + "days");
    }
}
