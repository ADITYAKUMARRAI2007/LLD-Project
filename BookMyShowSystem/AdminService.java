public class AdminService {
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;

    public AdminService(MovieRepository movieRepository,
                        TheaterRepository theaterRepository,
                        ShowRepository showRepository) {
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void addTheater(Theater theater) {
        theaterRepository.save(theater);
    }

    public void addShow(Show show) {
        showRepository.save(show);
    }
}