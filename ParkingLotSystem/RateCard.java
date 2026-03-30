import java.util.Map;

public class RateCard {
    private final Map<SlotType, Double> hourlyRates;

    public RateCard(Map<SlotType, Double> hourlyRates) {
        if (hourlyRates == null || hourlyRates.isEmpty()) {
            throw new IllegalArgumentException("Hourly rates cannot be null or empty.");
        }
        this.hourlyRates = hourlyRates;
    }

    public double getHourlyRate(SlotType slotType) {
        Double rate = hourlyRates.get(slotType);
        if (rate == null) {
            throw new IllegalArgumentException("No rate configured for slot type: " + slotType);
        }
        return rate;
    }
}