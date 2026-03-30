import java.util.List;

public class SSTFDispatchStrategy implements DispatchStrategy {
    @Override
    public ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request) {
        ElevatorCar best = null;
        int bestDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (!elevator.canServeRequests()) {
                continue;
            }
            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            if (distance < bestDistance) {
                bestDistance = distance;
                best = elevator;
            }
        }
        return best;
    }
}