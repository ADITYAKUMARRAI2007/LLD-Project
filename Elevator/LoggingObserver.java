public class LoggingObserver implements ElevatorObserver {
    private final LoggingService loggingService;

    public LoggingObserver(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public void onElevatorEvent(ElevatorCar elevator, String event) {
        loggingService.log("[Elevator " + elevator.getId() + "] " + event);
    }
}