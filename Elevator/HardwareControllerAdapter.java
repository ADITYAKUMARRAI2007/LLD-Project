public class HardwareControllerAdapter {
    public void moveUp(String elevatorId) {
        System.out.println("Hardware: Elevator " + elevatorId + " moving up");
    }

    public void moveDown(String elevatorId) {
        System.out.println("Hardware: Elevator " + elevatorId + " moving down");
    }

    public void stop(String elevatorId) {
        System.out.println("Hardware: Elevator " + elevatorId + " stopped");
    }

    public void openDoor(String elevatorId) {
        System.out.println("Hardware: Elevator " + elevatorId + " door opened");
    }

    public void closeDoor(String elevatorId) {
        System.out.println("Hardware: Elevator " + elevatorId + " door closed");
    }

    public void playWarning(String elevatorId, String message) {
        System.out.println("Hardware Warning [" + elevatorId + "]: " + message);
    }
}