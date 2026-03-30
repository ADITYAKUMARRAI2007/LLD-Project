import java.util.List;

public class Movie {
    private final String movieId;
    private final String title;
    private final List<MovieLanguage> languages;
    private final int durationInMinutes;
    private final String genre;

    public Movie(String movieId, String title, List<MovieLanguage> languages, int durationInMinutes, String genre) {
        this.movieId = movieId;
        this.title = title;
        this.languages = languages;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public List<MovieLanguage> getLanguages() {
        return languages;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", languages=" + languages +
                ", durationInMinutes=" + durationInMinutes +
                ", genre='" + genre + '\'' +
                '}';
    }
}