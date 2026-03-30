public class PrimeTimePricingRule implements PricingRule {

    @Override
    public double getIncrement(Show show, ShowSeat showSeat) {
        int hour = show.getStartTime().getHour();
        if (hour >= 18 && hour <= 22) {
            return 30.0;
        }
        return 0.0;
    }
}