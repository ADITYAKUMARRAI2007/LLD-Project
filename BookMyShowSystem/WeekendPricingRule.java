import java.time.DayOfWeek;

public class WeekendPricingRule implements PricingRule {

    @Override
    public double getIncrement(Show show, ShowSeat showSeat) {
        DayOfWeek day = show.getStartTime().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return 40.0;
        }
        return 0.0;
    }
}