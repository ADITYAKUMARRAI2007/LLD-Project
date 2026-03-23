import java.util.Map;

public class LegacyRoomPricingPolicy implements RoomPricingPolicy {
    private final Map<Integer, Money> rates = Map.of(
            LegacyRoomTypes.SINGLE, new Money(14000.0),
            LegacyRoomTypes.DOUBLE, new Money(15000.0),
            LegacyRoomTypes.TRIPLE, new Money(12000.0),
            LegacyRoomTypes.DELUXE, new Money(16000.0)
    );

    @Override
    public Money monthlyBaseFor(int roomType) {
        return rates.getOrDefault(roomType, rates.get(LegacyRoomTypes.DELUXE));
    }
}
