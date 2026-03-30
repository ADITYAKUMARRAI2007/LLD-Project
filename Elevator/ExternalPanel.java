public class ExternalPanel {
    private final int floorNumber;
    private final ElevatorSystem elevatorSystem;
    private final UpButton upButton;
    private final DownButton downButton;

    public ExternalPanel(int floorNumber, ElevatorSystem elevatorSystem) {
        this.floorNumber = floorNumber;
        this.elevatorSystem = elevatorSystem;
        this.upButton = new UpButton(this);
        this.downButton = new DownButton(this);
    }

    public void requestUp() {
        elevatorSystem.submitExternalRequest(new ExternalRequest(floorNumber, Direction.UP));
    }

    public void requestDown() {
        elevatorSystem.submitExternalRequest(new ExternalRequest(floorNumber, Direction.DOWN));
    }

    public UpButton getUpButton() {
        return upButton;
    }

    public DownButton getDownButton() {
        return downButton;
    }
}