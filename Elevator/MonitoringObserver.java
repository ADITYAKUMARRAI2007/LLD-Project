public class MonitoringObserver implements ElevatorObserver {
    private final MonitoringService monitoringService;

    public MonitoringObserver(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Override
    public void onElevatorEvent(ElevatorCar elevator, String event) {
        monitoringService.capture(elevator.getId(), event);
    }
}