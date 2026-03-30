public interface SlotAssignmentStrategy {
    ParkingSlot assignSlot(ParkingLot parkingLot, SlotType slotType, String entryGateId, Vehicle vehicle);
}