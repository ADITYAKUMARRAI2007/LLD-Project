import java.util.*;

public class MovieRepository {
    private final Map<String, Movie> moviesById = new HashMap<>();

    public void save(Movie movie) {
        moviesById.put(movie.getMovieId(), movie);
    }

    public Movie findById(String movieId) {
        return moviesById.get(movieId);
    }

    public List<Movie> findAll() {
        return new ArrayList<>(moviesById.values());
    }
}