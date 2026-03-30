public class ElevatorController {
    private final ElevatorCar elevator;

    public ElevatorController(ElevatorCar elevator) {
        this.elevator = elevator;
    }

    public void submitInternalRequest(int floor) {
        elevator.addInternalDestination(floor);
    }

    public void openDoor() {
        elevator.openDoor();
    }

    public void closeDoor() {
        elevator.closeDoor();
    }

    public void triggerAlarm() {
        elevator.getAlarm().press();
    }
}