import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final SeatLockService seatLockService;
    private final PricingService pricingService;
    private final PaymentService paymentService;
    private final RefundService refundService;

    public BookingService(ShowRepository showRepository,
                          BookingRepository bookingRepository,
                          SeatLockService seatLockService,
                          PricingService pricingService,
                          PaymentService paymentService,
                          RefundService refundService) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.seatLockService = seatLockService;
        this.pricingService = pricingService;
        this.paymentService = paymentService;
        this.refundService = refundService;
    }

    public Booking createBooking(User user, String showId, List<String> seatIds) {
        Show show = showRepository.findById(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found");
        }

        boolean locked = seatLockService.lockSeats(show, seatIds, user.getUserId());
        if (!locked) {
            throw new IllegalStateException("Seats are not available");
        }

        List<ShowSeat> selectedShowSeats = getSelectedShowSeats(show, seatIds);

        Booking booking = new Booking(UUID.randomUUID().toString(), user, show, selectedShowSeats);
        booking.setTotalAmount(pricingService.calculatePrice(show, selectedShowSeats));

        bookingRepository.save(booking);
        return booking;
    }

    public BookingReceipt confirmBooking(String bookingId, PaymentRequest paymentRequest) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        Payment payment = paymentService.makePayment(booking.getTotalAmount(), paymentRequest);

        if (payment.getStatus() != PaymentStatus.SUCCESS) {
            booking.setStatus(BookingStatus.EXPIRED);
            seatLockService.releaseSeats(
                    booking.getShow(),
                    getSeatIds(booking.getBookedSeats()),
                    booking.getUser().getUserId()
            );
            throw new IllegalStateException("Payment failed");
        }

        booking.setPayment(payment);
        seatLockService.confirmSeats(
                booking.getShow(),
                getSeatIds(booking.getBookedSeats()),
                booking.getUser().getUserId()
        );
        booking.setStatus(BookingStatus.CONFIRMED);

        return new BookingReceipt(
                booking.getBookingId(),
                booking.getShow().getMovie().getTitle(),
                booking.getShow().getTheater().getName(),
                booking.getShow().getScreen().getName(),
                booking.getBookedSeats().size(),
                booking.getTotalAmount()
        );
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed bookings can be cancelled");
        }

        for (ShowSeat showSeat : booking.getBookedSeats()) {
            showSeat.cancelBookedSeat();
        }

        boolean refunded = refundService.processRefund(booking);
        if (!refunded) {
            throw new IllegalStateException("Refund failed");
        }

        booking.setStatus(BookingStatus.CANCELLED);
    }

    private List<ShowSeat> getSelectedShowSeats(Show show, List<String> seatIds) {
        List<ShowSeat> selectedSeats = new ArrayList<>();
        for (ShowSeat showSeat : show.getShowSeats()) {
            if (seatIds.contains(showSeat.getSeat().getSeatId())) {
                selectedSeats.add(showSeat);
            }
        }
        return selectedSeats;
    }

    private List<String> getSeatIds(List<ShowSeat> showSeats) {
        List<String> seatIds = new ArrayList<>();
        for (ShowSeat showSeat : showSeats) {
            seatIds.add(showSeat.getSeat().getSeatId());
        }
        return seatIds;
    }
}