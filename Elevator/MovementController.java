public class MovementController {
    private final HardwareControllerAdapter hardware;

    public MovementController(HardwareControllerAdapter hardware) {
        this.hardware = hardware;
    }

    public void moveUp(ElevatorCar elevator) {
        hardware.moveUp(elevator.getId());
    }

    public void moveDown(ElevatorCar elevator) {
        hardware.moveDown(elevator.getId());
    }

    public void stop(ElevatorCar elevator) {
        hardware.stop(elevator.getId());
    }
}