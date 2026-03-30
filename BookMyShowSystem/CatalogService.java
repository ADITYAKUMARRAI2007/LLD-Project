import java.util.List;

public class CatalogService {
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;

    public CatalogService(TheaterRepository theaterRepository, ShowRepository showRepository) {
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
    }

    public List<Theater> getTheatersByCity(String cityId) {
        return theaterRepository.findByCity(cityId);
    }

    public List<Movie> getMoviesByCity(String cityId) {
        return showRepository.findMoviesByCity(cityId);
    }

    public List<Show> getShowsForMovie(String movieId, String cityId) {
        return showRepository.findByMovieInCity(movieId, cityId);
    }

    public List<Movie> getMoviesForTheater(String theaterId) {
        return showRepository.findMoviesByTheater(theaterId);
    }

    public List<Show> getShowsForTheater(String theaterId) {
        return showRepository.findByTheater(theaterId);
    }
}