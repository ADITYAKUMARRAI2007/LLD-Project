public class SeatCategoryPricingRule implements PricingRule {

    @Override
    public double getIncrement(Show show, ShowSeat showSeat) {
        SeatCategory category = showSeat.getSeat().getSeatCategory();

        if (category == SeatCategory.PREMIUM) {
            return 50.0;
        }
        if (category == SeatCategory.RECLINER) {
            return 120.0;
        }
        return 0.0;
    }
}