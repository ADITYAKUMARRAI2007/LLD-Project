import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final Building building;
    private final List<ElevatorCar> elevators;
    private final Dispatcher dispatcher;
    private final ElevatorService elevatorService;
    private final EmergencyService emergencyService;
    private final MaintenanceService maintenanceService;
    private final RequestScheduler requestScheduler;

    public ElevatorSystem(Building building,
                          Dispatcher dispatcher,
                          ElevatorService elevatorService,
                          EmergencyService emergencyService,
                          MaintenanceService maintenanceService,
                          RequestScheduler requestScheduler) {
        this.building = building;
        this.elevators = new ArrayList<>();
        this.dispatcher = dispatcher;
        this.elevatorService = elevatorService;
        this.emergencyService = emergencyService;
        this.maintenanceService = maintenanceService;
        this.requestScheduler = requestScheduler;
    }

    public void addElevator(ElevatorCar elevator) {
        elevators.add(elevator);
    }

    public void submitExternalRequest(ExternalRequest request) {
        requestScheduler.submit(request);
    }

    public void processRequests() {
        while (requestScheduler.hasRequests()) {
            ElevatorRequest request = requestScheduler.poll();
            if (request instanceof ExternalRequest externalRequest) {
                ElevatorCar assigned = dispatcher.dispatch(elevators, externalRequest);
                if (assigned != null) {
                    assigned.addExternalPickup(externalRequest.getSourceFloor());
                }
            }
        }
    }

    public void stepSystem() {
        processRequests();
        elevatorService.stepAll(elevators);
    }

    public void triggerBuildingEmergency() {
        emergencyService.triggerBuildingEmergency(elevators);
    }

    public void clearBuildingEmergency() {
        emergencyService.clearBuildingEmergency(elevators);
    }

    public void putElevatorInMaintenance(String elevatorId) {
        ElevatorCar elevator = findElevatorById(elevatorId);
        if (elevator != null) {
            maintenanceService.putInMaintenance(elevator);
        }
    }

    public void bringElevatorOutOfMaintenance(String elevatorId) {
        ElevatorCar elevator = findElevatorById(elevatorId);
        if (elevator != null) {
            maintenanceService.bringOutOfMaintenance(elevator);
        }
    }

    public ElevatorCar findElevatorById(String id) {
        for (ElevatorCar elevator : elevators) {
            if (elevator.getId().equals(id)) {
                return elevator;
            }
        }
        return null;
    }

    public Building getBuilding() {
        return building;
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}