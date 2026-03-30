import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> bookingsById = new HashMap<>();

    public void save(Booking booking) {
        bookingsById.put(booking.getBookingId(), booking);
    }

    public Booking findById(String bookingId) {
        return bookingsById.get(bookingId);
    }
}