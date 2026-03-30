public class MaintenanceService {
    public void putInMaintenance(ElevatorCar elevator) {
        elevator.enterMaintenance();
    }

    public void bringOutOfMaintenance(ElevatorCar elevator) {
        elevator.exitMaintenance();
    }
}