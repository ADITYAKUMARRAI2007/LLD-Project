import java.util.List;

public class Theater {
    private final String theaterId;
    private final String name;
    private final City city;
    private final List<Screen> screens;

    public Theater(String theaterId, String name, City city, List<Screen> screens) {
        this.theaterId = theaterId;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId='" + theaterId + '\'' +
                ", name='" + name + '\'' +
                ", city=" + city.getName() +
                '}';
    }
}