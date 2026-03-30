import java.util.List;

public interface DispatchStrategy {
    ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request);
}