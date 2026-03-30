import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieRepository movieRepository = new MovieRepository();
        TheaterRepository theaterRepository = new TheaterRepository();
        ShowRepository showRepository = new ShowRepository();
        BookingRepository bookingRepository = new BookingRepository();

        PricingStrategy pricingStrategy = new RuleBasedPricingStrategy(
                Arrays.asList(
                        new WeekendPricingRule(),
                        new PrimeTimePricingRule(),
                        new SeatCategoryPricingRule()
                ),
                150.0,
                220.0,
                400.0
        );

        PricingService pricingService = new PricingService(pricingStrategy);
        PaymentService paymentService = new PaymentService();
        RefundService refundService = new RefundService(paymentService);
        SeatLockService seatLockService = new SeatLockService(5);

        BookingService bookingService = new BookingService(
                showRepository,
                bookingRepository,
                seatLockService,
                pricingService,
                paymentService,
                refundService
        );

        CatalogService catalogService = new CatalogService(theaterRepository, showRepository);
        ShowService showService = new ShowService(showRepository);
        AdminService adminService = new AdminService(movieRepository, theaterRepository, showRepository);

        City bangalore = new City("C1", "Bangalore");

        Movie movie = new Movie(
                "M1",
                "Interstellar",
                Arrays.asList(MovieLanguage.ENGLISH, MovieLanguage.HINDI),
                169,
                "Sci-Fi"
        );

        List<Seat> seats = Arrays.asList(
                new Seat("S1", "A1", SeatCategory.REGULAR),
                new Seat("S2", "A2", SeatCategory.REGULAR),
                new Seat("S3", "B1", SeatCategory.PREMIUM),
                new Seat("S4", "C1", SeatCategory.RECLINER)
        );

        Screen screen = new Screen("SC1", "Screen 1", seats);
        Theater theater = new Theater("T1", "PVR Orion", bangalore, Arrays.asList(screen));

        Show show = new Show(
                "SH1",
                movie,
                theater,
                screen,
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(5),
                new ArrayList<>()
        );

        List<ShowSeat> showSeats = new ArrayList<>();
        for (Seat seat : seats) {
            showSeats.add(new ShowSeat("SS-" + seat.getSeatId(), seat, show));
        }

        Show finalShow = new Show(
                "SH1",
                movie,
                theater,
                screen,
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(5),
                showSeats
        );

        adminService.addMovie(movie);
        adminService.addTheater(theater);
        adminService.addShow(finalShow);

        User user = new User("U1", "Aditya", "aditya@example.com", "9999999999", UserRole.CUSTOMER);

        System.out.println("Movies in Bangalore:");
        System.out.println(catalogService.getMoviesByCity("C1"));

        System.out.println("\nTheaters in Bangalore:");
        System.out.println(catalogService.getTheatersByCity("C1"));

        System.out.println("\nShows for movie:");
        System.out.println(catalogService.getShowsForMovie("M1", "C1"));

        System.out.println("\nSeat Map:");
        for (ShowSeat ss : showService.getSeatMap("SH1")) {
            System.out.println(ss.getSeat().getSeatNumber() + " -> " + ss.getState());
        }

        Booking booking = bookingService.createBooking(user, "SH1", Arrays.asList("S1", "S3"));
        System.out.println("\nBooking created:");
        System.out.println(booking);

        BookingReceipt receipt = bookingService.confirmBooking(
                booking.getBookingId(),
                new PaymentRequest(PaymentMethod.UPI)
        );

        System.out.println("\nBooking confirmed:");
        System.out.println(receipt);

        bookingService.cancelBooking(booking.getBookingId());
        System.out.println("\nBooking cancelled and refund processed.");
    }
}