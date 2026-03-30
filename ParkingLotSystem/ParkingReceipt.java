import java.time.LocalDateTime;

public class ParkingReceipt {
    private final String ticketId;
    private final String vehicleNumber;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;
    private final double amount;
    private final String exitGateId;

    public ParkingReceipt(String ticketId, String vehicleNumber, LocalDateTime entryTime,
                          LocalDateTime exitTime, double amount, String exitGateId) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.amount = amount;
        this.exitGateId = exitGateId;
    }

    @Override
    public String toString() {
        return "ParkingReceipt{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amount=" + amount +
                ", exitGateId='" + exitGateId + '\'' +
                '}';
    }
}