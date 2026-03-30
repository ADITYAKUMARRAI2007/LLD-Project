import java.util.List;

public interface PricingStrategy {
    double calculatePrice(Show show, List<ShowSeat> showSeats);
}