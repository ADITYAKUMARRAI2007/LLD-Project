import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final String entryGateId;
    private final LocalDateTime entryTime;
    private TicketStatus status;

    public Ticket(Vehicle vehicle, ParkingSlot slot, String entryGateId, LocalDateTime entryTime) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        if (slot == null) {
            throw new IllegalArgumentException("Slot cannot be null.");
        }
        if (entryGateId == null || entryGateId.isBlank()) {
            throw new IllegalArgumentException("Entry gate id cannot be null or blank.");
        }
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry time cannot be null.");
        }

        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryGateId = entryGateId;
        this.entryTime = entryTime;
        this.status = TicketStatus.ACTIVE;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public String getEntryGateId() {
        return entryGateId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void close() {
        this.status = TicketStatus.CLOSED;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", slot=" + slot +
                ", entryGateId='" + entryGateId + '\'' +
                ", entryTime=" + entryTime +
                ", status=" + status +
                '}';
    }
}