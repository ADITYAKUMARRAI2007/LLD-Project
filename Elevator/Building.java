import java.util.ArrayList;
import java.util.List;

public class Building {
    private final String buildingId;
    private final List<Floor> floors;
    private ElevatorSystem elevatorSystem;

    public Building(String buildingId) {
        this.buildingId = buildingId;
        this.floors = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void removeFloor(int floorNumber) {
        floors.removeIf(floor -> floor.getFloorNumber() == floorNumber);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public ElevatorSystem getElevatorSystem() {
        return elevatorSystem;
    }

    public void setElevatorSystem(ElevatorSystem elevatorSystem) {
        this.elevatorSystem = elevatorSystem;
    }
}