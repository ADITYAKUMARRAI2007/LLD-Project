import java.util.List;

public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<ShowSeat> getSeatMap(String showId) {
        Show show = showRepository.findById(showId);
        if (show == null) {
            throw new IllegalArgumentException("Show not found");
        }
        return show.getShowSeats();
    }
}