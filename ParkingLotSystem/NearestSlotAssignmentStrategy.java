import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;

public class NearestSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public ParkingSlot assignSlot(ParkingLot parkingLot, SlotType slotType, String entryGateId, Vehicle vehicle) {
        if (parkingLot == null) {
            throw new IllegalArgumentException("ParkingLot cannot be null.");
        }
        if (slotType == null) {
            throw new IllegalArgumentException("SlotType cannot be null.");
        }
        if (entryGateId == null || entryGateId.isBlank()) {
            throw new IllegalArgumentException("Entry gate id cannot be null or blank.");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        Map<SlotType, NavigableSet<ParkingSlot>> perGateSlots = parkingLot.getAvailableSlotsByGate().get(entryGateId);
        if (perGateSlots == null) {
            throw new IllegalArgumentException("Unknown entry gate: " + entryGateId);
        }

        NavigableSet<ParkingSlot> candidates = perGateSlots.get(slotType);
        if (candidates == null || candidates.isEmpty()) {
            return null;
        }

        Iterator<ParkingSlot> iterator = candidates.iterator();
        while (iterator.hasNext()) {
            ParkingSlot slot = iterator.next();

            if (slot.tryReserve(vehicle)) {
                parkingLot.removeSlotFromAllGateIndexes(slot);
                return slot;
            }
        }

        return null;
    }
}