import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

public class ParkingLot {
    private static volatile ParkingLot instance;

    private final List<ParkingLevel> levels;
    private final Map<String, EntryGate> entryGates;
    private final Map<String, ExitGate> exitGates;
    private final Map<String, Ticket> activeTickets;
    private final Map<String, Map<SlotType, NavigableSet<ParkingSlot>>> availableSlotsByGate;

    private SlotAssignmentStrategy slotAssignmentStrategy;
    private PricingStrategy pricingStrategy;

    private ParkingLot(List<ParkingLevel> levels,
                       Map<String, EntryGate> entryGates,
                       Map<String, ExitGate> exitGates,
                       Map<String, Ticket> activeTickets,
                       Map<String, Map<SlotType, NavigableSet<ParkingSlot>>> availableSlotsByGate) {
        this.levels = levels;
        this.entryGates = entryGates;
        this.exitGates = exitGates;
        this.activeTickets = activeTickets;
        this.availableSlotsByGate = availableSlotsByGate;
    }

    public static ParkingLot init(List<ParkingLevel> levels,
                                  Map<String, EntryGate> entryGates,
                                  Map<String, ExitGate> exitGates,
                                  Map<String, Ticket> activeTickets,
                                  Map<String, Map<SlotType, NavigableSet<ParkingSlot>>> availableSlotsByGate) {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) {
                    instance = new ParkingLot(levels, entryGates, exitGates, activeTickets, availableSlotsByGate);
                }
            }
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ParkingLot is not initialized yet.");
        }
        return instance;
    }

    public void setSlotAssignmentStrategy(SlotAssignmentStrategy slotAssignmentStrategy) {
        if (slotAssignmentStrategy == null) {
            throw new IllegalArgumentException("SlotAssignmentStrategy cannot be null.");
        }
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        if (pricingStrategy == null) {
            throw new IllegalArgumentException("PricingStrategy cannot be null.");
        }
        this.pricingStrategy = pricingStrategy;
    }

    public List<ParkingLevel> getLevels() {
        return levels;
    }

    public Map<String, EntryGate> getEntryGates() {
        return entryGates;
    }

    public Map<String, ExitGate> getExitGates() {
        return exitGates;
    }

    public Map<String, Ticket> getActiveTickets() {
        return activeTickets;
    }

    public Map<String, Map<SlotType, NavigableSet<ParkingSlot>>> getAvailableSlotsByGate() {
        return availableSlotsByGate;
    }

    public SlotAssignmentStrategy getSlotAssignmentStrategy() {
        return slotAssignmentStrategy;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void removeSlotFromAllGateIndexes(ParkingSlot slot) {
        for (Map<SlotType, NavigableSet<ParkingSlot>> perGateMap : availableSlotsByGate.values()) {
            perGateMap.get(slot.getSlotType()).remove(slot);
        }
    }

    public void addSlotToAllGateIndexes(ParkingSlot slot) {
        for (Map<SlotType, NavigableSet<ParkingSlot>> perGateMap : availableSlotsByGate.values()) {
            perGateMap.get(slot.getSlotType()).add(slot);
        }
    }
}