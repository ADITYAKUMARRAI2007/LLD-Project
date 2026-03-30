import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricingStrategy implements PricingStrategy {
    private final RateCard rateCard;

    public HourlyPricingStrategy(RateCard rateCard) {
        if (rateCard == null) {
            throw new IllegalArgumentException("RateCard cannot be null.");
        }
        this.rateCard = rateCard;
    }

    @Override
    public double calculateAmount(Ticket ticket, LocalDateTime exitTime) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null.");
        }
        if (exitTime == null) {
            throw new IllegalArgumentException("Exit time cannot be null.");
        }

        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = (minutes + 59) / 60;
        if (hours <= 0) {
            hours = 1;
        }

        return hours * rateCard.getHourlyRate(ticket.getSlot().getSlotType());
    }
}