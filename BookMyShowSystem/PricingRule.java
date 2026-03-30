public interface PricingRule {
    double getIncrement(Show show, ShowSeat showSeat);
}