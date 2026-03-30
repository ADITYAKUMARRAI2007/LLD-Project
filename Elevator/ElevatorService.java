import java.util.List;

public class ElevatorService {
    public void stepAll(List<ElevatorCar> elevators) {
        for (ElevatorCar elevator : elevators) {
            elevator.step();
        }
    }
}