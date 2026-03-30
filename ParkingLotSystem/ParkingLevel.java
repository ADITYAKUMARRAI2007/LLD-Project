import java.util.Map;

public class ParkingLevel {
    private final int levelNumber;
    private final Map<String, ParkingSlot> slots;

    public ParkingLevel(int levelNumber, Map<String, ParkingSlot> slots) {
        if (slots == null) {
            throw new IllegalArgumentException("Slots map cannot be null.");
        }

        this.levelNumber = levelNumber;
        this.slots = slots;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public Map<String, ParkingSlot> getSlots() {
        return slots;
    }

    @Override
    public String toString() {
        return "ParkingLevel{" +
                "levelNumber=" + levelNumber +
                ", slotsCount=" + slots.size() +
                '}';
    }
}