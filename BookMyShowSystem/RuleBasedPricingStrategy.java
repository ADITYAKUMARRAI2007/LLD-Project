import java.util.List;

public class RuleBasedPricingStrategy implements PricingStrategy {
    private final List<PricingRule> rules;
    private final double regularBasePrice;
    private final double premiumBasePrice;
    private final double reclinerBasePrice;

    public RuleBasedPricingStrategy(List<PricingRule> rules,
                                    double regularBasePrice,
                                    double premiumBasePrice,
                                    double reclinerBasePrice) {
        this.rules = rules;
        this.regularBasePrice = regularBasePrice;
        this.premiumBasePrice = premiumBasePrice;
        this.reclinerBasePrice = reclinerBasePrice;
    }

    public double calculatePrice(Show show, List<ShowSeat> showSeats) {
        double total = 0.0;

        for (ShowSeat showSeat : showSeats) {
            double seatPrice = getBasePrice(showSeat.getSeat().getSeatCategory());

            for (PricingRule rule : rules) {
                double increment = rule.getIncrement(show, showSeat);
                if (increment > 0) {
                    seatPrice += increment;
                }
            }

            total += seatPrice;
        }

        return total;
    }

    private double getBasePrice(SeatCategory category) {
        if (category == SeatCategory.REGULAR) {
            return regularBasePrice;
        }
        if (category == SeatCategory.PREMIUM) {
            return premiumBasePrice;
        }
        return reclinerBasePrice;
    }
}