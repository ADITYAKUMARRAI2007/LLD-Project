public class Floor {
    private final int floorNumber;
    private ExternalPanel externalPanel;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public ExternalPanel getExternalPanel() {
        return externalPanel;
    }

    public void setExternalPanel(ExternalPanel externalPanel) {
        this.externalPanel = externalPanel;
    }
}