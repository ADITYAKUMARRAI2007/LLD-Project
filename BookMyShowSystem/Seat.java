public class Seat {
    private final String seatId;
    private final String seatNumber;
    private final SeatCategory seatCategory;

    public Seat(String seatId, String seatNumber, SeatCategory seatCategory) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatCategory = seatCategory;
    }

    public String getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
}