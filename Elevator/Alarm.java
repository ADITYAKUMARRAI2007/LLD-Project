public class Alarm {
    private ElevatorCar elevator;

    public void bindElevator(ElevatorCar elevator) {
        this.elevator = elevator;
    }

    public void press() {
        if (elevator != null) {
            elevator.emergencyStop("Alarm button pressed");
        }
    }
}