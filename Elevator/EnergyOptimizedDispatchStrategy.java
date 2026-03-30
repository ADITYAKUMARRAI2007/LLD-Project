import java.util.List;

public class EnergyOptimizedDispatchStrategy implements DispatchStrategy {
    @Override
    public ElevatorCar chooseElevator(List<ElevatorCar> elevators, ExternalRequest request) {
        ElevatorCar best = null;
        int bestScore = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            if (!elevator.canServeRequests()) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - request.getSourceFloor());
            int directionPenalty = 0;

            if (elevator.getDirection() != Direction.IDLE &&
                elevator.getDirection() != request.getDesiredDirection()) {
                directionPenalty = 5;
            }

            int score = distance + directionPenalty;
            if (score < bestScore) {
                bestScore = score;
                best = elevator;
            }
        }

        return best;
    }
}