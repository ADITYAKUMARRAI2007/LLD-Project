import java.time.LocalDateTime;

public class ParkingManager {
    private final ParkingLot parkingLot;

    public ParkingManager(ParkingLot parkingLot) {
        if (parkingLot == null) {
            throw new IllegalArgumentException("ParkingLot cannot be null.");
        }
        this.parkingLot = parkingLot;
    }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime, SlotType desiredSlotType, String entryGateId) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time cannot be null.");
        }
        if (desiredSlotType == null) {
            throw new IllegalArgumentException("Desired slot type cannot be null.");
        }
        if (entryGateId == null || entryGateId.isBlank()) {
            throw new IllegalArgumentException("Entry gate id cannot be null or blank.");
        }

        ParkingSlot assignedSlot = parkingLot.getSlotAssignmentStrategy()
                .assignSlot(parkingLot, desiredSlotType, entryGateId, vehicle);

        if (assignedSlot == null) {
            throw new IllegalStateException("No available slot found.");
        }

        Ticket ticket = new Ticket(vehicle, assignedSlot, entryGateId, entryTime);
        parkingLot.getActiveTickets().put(ticket.getTicketId(), ticket);

        return ticket;
    }

    public ParkingReceipt exit(String ticketId, LocalDateTime exitTime, String exitGateId) {
        if (ticketId == null || ticketId.isBlank()) {
            throw new IllegalArgumentException("Ticket id cannot be null or blank.");
        }
        if (exitTime == null) {
            throw new IllegalArgumentException("Exit time cannot be null.");
        }
        if (exitGateId == null || exitGateId.isBlank()) {
            throw new IllegalArgumentException("Exit gate id cannot be null or blank.");
        }

        Ticket ticket = parkingLot.getActiveTickets().get(ticketId);

        if (ticket == null || ticket.getStatus() == TicketStatus.CLOSED) {
            throw new IllegalArgumentException("Invalid or already closed ticket.");
        }

        double amount = parkingLot.getPricingStrategy().calculateAmount(ticket, exitTime);

        ParkingSlot slot = ticket.getSlot();
        slot.release();
        parkingLot.addSlotToAllGateIndexes(slot);

        ticket.close();
        parkingLot.getActiveTickets().remove(ticketId);

        return new ParkingReceipt(
                ticket.getTicketId(),
                ticket.getVehicle().getVehicleNumber(),
                ticket.getEntryTime(),
                exitTime,
                amount,
                exitGateId
        );
    }
}