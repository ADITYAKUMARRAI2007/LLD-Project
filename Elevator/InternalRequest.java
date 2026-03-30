public class InternalRequest extends ElevatorRequest {
    private final int targetFloor;

    public InternalRequest(int sourceFloor, int targetFloor) {
        super(RequestType.INTERNAL, sourceFloor);
        this.targetFloor = targetFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }
}