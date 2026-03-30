import java.util.List;

public class Booking {
    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<ShowSeat> bookedSeats;
    private double totalAmount;
    private BookingStatus status;
    private Payment payment;

    public Booking(String bookingId, User user, Show show, List<ShowSeat> bookedSeats) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.bookedSeats = bookedSeats;
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getBookedSeats() {
        return bookedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", user=" + user.getName() +
                ", show=" + show.getShowId() +
                ", seatCount=" + bookedSeats.size() +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}