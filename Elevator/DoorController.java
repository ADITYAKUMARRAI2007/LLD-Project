public class DoorController {
    private final HardwareControllerAdapter hardware;

    public DoorController(HardwareControllerAdapter hardware) {
        this.hardware = hardware;
    }

    public void open(ElevatorCar elevator) {
        hardware.openDoor(elevator.getId());
        elevator.getDoor().open();
    }

    public void close(ElevatorCar elevator) {
        hardware.closeDoor(elevator.getId());
        elevator.getDoor().close();
    }
}