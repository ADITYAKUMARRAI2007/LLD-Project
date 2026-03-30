public class Door {
    private DoorState state = DoorState.CLOSED;

    public void open() {
        state = DoorState.OPEN;
    }

    public void close() {
        state = DoorState.CLOSED;
    }

    public DoorState getState() {
        return state;
    }
}