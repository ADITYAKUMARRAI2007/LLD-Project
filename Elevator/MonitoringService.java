public class MonitoringService {
    public void capture(String elevatorId, String event) {
        System.out.println("[MONITOR] Elevator=" + elevatorId + " Event=" + event);
    }
}