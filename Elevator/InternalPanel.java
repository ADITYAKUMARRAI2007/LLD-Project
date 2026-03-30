import java.util.ArrayList;
import java.util.List;

public class InternalPanel {
    private final ElevatorController elevatorController;
    private final List<FloorButton> floorButtons;
    private final OpenDoorButton openDoorButton;
    private final CloseDoorButton closeDoorButton;
    private final AlarmButton alarmButton;

    public InternalPanel(ElevatorController elevatorController, int maxFloor) {
        this.elevatorController = elevatorController;
        this.floorButtons = new ArrayList<>();

        for (int i = 0; i <= maxFloor; i++) {
            floorButtons.add(new FloorButton(this, i));
        }

        this.openDoorButton = new OpenDoorButton(this);
        this.closeDoorButton = new CloseDoorButton(this);
        this.alarmButton = new AlarmButton(this);
    }

    public void selectFloor(int floor) {
        elevatorController.submitInternalRequest(floor);
    }

    public void openDoor() {
        elevatorController.openDoor();
    }

    public void closeDoor() {
        elevatorController.closeDoor();
    }

    public void triggerAlarm() {
        elevatorController.triggerAlarm();
    }

    public List<FloorButton> getFloorButtons() {
        return floorButtons;
    }

    public OpenDoorButton getOpenDoorButton() {
        return openDoorButton;
    }

    public CloseDoorButton getCloseDoorButton() {
        return closeDoorButton;
    }

    public AlarmButton getAlarmButton() {
        return alarmButton;
    }
}