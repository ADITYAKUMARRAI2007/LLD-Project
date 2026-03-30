import java.util.List;

public class EmergencyService {
    private boolean buildingEmergency;

    public void triggerBuildingEmergency(List<ElevatorCar> elevators) {
        buildingEmergency = true;
        for (ElevatorCar elevator : elevators) {
            elevator.emergencyStop("Building-wide emergency triggered");
        }
    }

    public void clearBuildingEmergency(List<ElevatorCar> elevators) {
        buildingEmergency = false;
        for (ElevatorCar elevator : elevators) {
            elevator.resetFromEmergency();
        }
    }

    public boolean isBuildingEmergency() {
        return buildingEmergency;
    }
}