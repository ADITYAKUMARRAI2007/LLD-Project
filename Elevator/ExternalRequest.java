public class ExternalRequest extends ElevatorRequest {
    private final Direction desiredDirection;

    public ExternalRequest(int sourceFloor, Direction desiredDirection) {
        super(RequestType.EXTERNAL, sourceFloor);
        this.desiredDirection = desiredDirection;
    }

    public Direction getDesiredDirection() {
        return desiredDirection;
    }
}