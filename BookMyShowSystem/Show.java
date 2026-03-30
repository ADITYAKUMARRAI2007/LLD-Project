import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private final String showId;
    private final Movie movie;
    private final Theater theater;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final List<ShowSeat> showSeats;

    public Show(String showId, Movie movie, Theater theater, Screen screen,
                LocalDateTime startTime, LocalDateTime endTime, List<ShowSeat> showSeats) {
        this.showId = showId;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showSeats = showSeats;
    }

    public String getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                ", movie=" + movie.getTitle() +
                ", theater=" + theater.getName() +
                ", screen=" + screen.getName() +
                ", startTime=" + startTime +
                '}';
    }
}