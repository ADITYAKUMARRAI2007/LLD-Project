public class Main {
    public static void main(String[] args) {
        Building building = new Building("B1");

        Dispatcher dispatcher = new Dispatcher(new EnergyOptimizedDispatchStrategy());
        ElevatorService elevatorService = new ElevatorService();
        EmergencyService emergencyService = new EmergencyService();
        MaintenanceService maintenanceService = new MaintenanceService();
        RequestScheduler requestScheduler = new RequestScheduler();
        AnnouncementService announcementService = new AnnouncementService();
        LoggingService loggingService = new LoggingService();
        MonitoringService monitoringService = new MonitoringService();

        ElevatorSystem elevatorSystem = new ElevatorSystem(
                building,
                dispatcher,
                elevatorService,
                emergencyService,
                maintenanceService,
                requestScheduler
        );

        building.setElevatorSystem(elevatorSystem);

        for (int i = 0; i <= 10; i++) {
            Floor floor = new Floor(i);
            floor.setExternalPanel(PanelFactory.createExternalPanel(i, elevatorSystem));
            building.addFloor(floor);
        }

        ElevatorCar elevator1 = ElevatorFactory.createElevator("E1", 0, 700, 10, announcementService);
        ElevatorCar elevator2 = ElevatorFactory.createElevator("E2", 0, 900, 10, announcementService);

        elevator1.addObserver(new LoggingObserver(loggingService));
        elevator1.addObserver(new MonitoringObserver(monitoringService));
        elevator2.addObserver(new LoggingObserver(loggingService));
        elevator2.addObserver(new MonitoringObserver(monitoringService));

        elevatorSystem.addElevator(elevator1);
        elevatorSystem.addElevator(elevator2);

        System.out.println("=== External floor request ===");
        building.getFloors().get(3).getExternalPanel().getUpButton().press();

        for (int i = 0; i < 5; i++) {
            elevatorSystem.stepSystem();
        }

        System.out.println("\n=== Internal request in elevator E1 ===");
        elevator1.getInternalPanel().selectFloor(8);

        for (int i = 0; i < 6; i++) {
            elevatorSystem.stepSystem();
        }

        System.out.println("\n=== Overload scenario ===");
        elevator1.updateCurrentWeight(750);
        elevator1.closeDoor();
        elevatorSystem.stepSystem();

        System.out.println("\n=== Maintenance mode ===");
        elevatorSystem.putElevatorInMaintenance("E2");
        building.getFloors().get(1).getExternalPanel().getDownButton().press();
        elevatorSystem.stepSystem();

        System.out.println("\n=== Building-wide emergency ===");
        elevatorSystem.triggerBuildingEmergency();
        elevatorSystem.stepSystem();

        System.out.println("\n=== Clear emergency ===");
        elevatorSystem.clearBuildingEmergency();
        elevatorSystem.stepSystem();
    }
}