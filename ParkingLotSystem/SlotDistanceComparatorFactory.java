import java.util.Comparator;
import java.util.Map;

public class SlotDistanceComparatorFactory {

    private SlotDistanceComparatorFactory() {
    }

    public static Comparator<ParkingSlot> create(Map<String, Integer> distanceBySlotId) {
        if (distanceBySlotId == null) {
            throw new IllegalArgumentException("Distance map cannot be null.");
        }

        return (slot1, slot2) -> {
            int d1 = distanceBySlotId.get(slot1.getSlotId());
            int d2 = distanceBySlotId.get(slot2.getSlotId());

            if (d1 != d2) {
                return Integer.compare(d1, d2);
            }

            if (slot1.getLevelNumber() != slot2.getLevelNumber()) {
                return Integer.compare(slot1.getLevelNumber(), slot2.getLevelNumber());
            }

            return slot1.getSlotId().compareTo(slot2.getSlotId());
        };
    }
}