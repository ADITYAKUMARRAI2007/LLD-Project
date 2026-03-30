import java.util.List;

public class FCFSDispatchStrategy implements DispatchStrategy {
    @Override
    public ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.canServeRequests()) {
                return elevator;
            }
        }
        return null;
    }
}