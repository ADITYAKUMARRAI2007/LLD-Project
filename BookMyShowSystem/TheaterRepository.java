import java.util.*;
import java.util.stream.Collectors;

public class TheaterRepository {
    private final Map<String, Theater> theatersById = new HashMap<>();

    public void save(Theater theater) {
        theatersById.put(theater.getTheaterId(), theater);
    }

    public Theater findById(String theaterId) {
        return theatersById.get(theaterId);
    }

    public List<Theater> findByCity(String cityId) {
        return theatersById.values().stream()
                .filter(theater -> theater.getCity().getCityId().equals(cityId))
                .collect(Collectors.toList());
    }

    public List<Theater> findAll() {
        return new ArrayList<>(theatersById.values());
    }
}