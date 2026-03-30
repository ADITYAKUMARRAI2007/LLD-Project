public class PanelFactory {
    private PanelFactory() {
    }

    public static ExternalPanel createExternalPanel(int floorNumber, ElevatorSystem elevatorSystem) {
        return new ExternalPanel(floorNumber, elevatorSystem);
    }
}