public class BookingReceipt {
    private final String bookingId;
    private final String movieName;
    private final String theaterName;
    private final String screenName;
    private final int seatCount;
    private final double amount;

    public BookingReceipt(String bookingId, String movieName, String theaterName,
                          String screenName, int seatCount, double amount) {
        this.bookingId = bookingId;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.screenName = screenName;
        this.seatCount = seatCount;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookingReceipt{" +
                "bookingId='" + bookingId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", theaterName='" + theaterName + '\'' +
                ", screenName='" + screenName + '\'' +
                ", seatCount=" + seatCount +
                ", amount=" + amount +
                '}';
    }
}