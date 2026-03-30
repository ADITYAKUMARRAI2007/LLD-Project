public class FloorButton extends Button {
    private final InternalPanel panel;
    private final int floorNumber;

    public FloorButton(InternalPanel panel, int floorNumber) {
        super("FLOOR_" + floorNumber);
        this.panel = panel;
        this.floorNumber = floorNumber;
    }

    @Override
    public void press() {
        panel.selectFloor(floorNumber);
    }
}