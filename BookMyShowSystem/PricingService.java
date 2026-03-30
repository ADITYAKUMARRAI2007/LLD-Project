import java.util.List;

public class PricingService {
    private final PricingStrategy pricingStrategy;

    public PricingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(Show show, List<ShowSeat> showSeats) {
        return pricingStrategy.calculatePrice(show, showSeats);
    }
}