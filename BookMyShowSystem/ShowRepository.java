import java.util.*;
import java.util.stream.Collectors;

public class ShowRepository {
    private final Map<String, Show> showsById = new HashMap<>();

    public void save(Show show) {
        showsById.put(show.getShowId(), show);
    }

    public Show findById(String showId) {
        return showsById.get(showId);
    }

    public List<Show> findByMovieInCity(String movieId, String cityId) {
        return showsById.values().stream()
                .filter(show -> show.getMovie().getMovieId().equals(movieId))
                .filter(show -> show.getTheater().getCity().getCityId().equals(cityId))
                .collect(Collectors.toList());
    }

    public List<Show> findByTheater(String theaterId) {
        return showsById.values().stream()
                .filter(show -> show.getTheater().getTheaterId().equals(theaterId))
                .collect(Collectors.toList());
    }

    public List<Movie> findMoviesByTheater(String theaterId) {
        Map<String, Movie> uniqueMovies = new LinkedHashMap<>();
        for (Show show : showsById.values()) {
            if (show.getTheater().getTheaterId().equals(theaterId)) {
                uniqueMovies.put(show.getMovie().getMovieId(), show.getMovie());
            }
        }
        return new ArrayList<>(uniqueMovies.values());
    }

    public List<Movie> findMoviesByCity(String cityId) {
        Map<String, Movie> uniqueMovies = new LinkedHashMap<>();
        for (Show show : showsById.values()) {
            if (show.getTheater().getCity().getCityId().equals(cityId)) {
                uniqueMovies.put(show.getMovie().getMovieId(), show.getMovie());
            }
        }
        return new ArrayList<>(uniqueMovies.values());
    }
}