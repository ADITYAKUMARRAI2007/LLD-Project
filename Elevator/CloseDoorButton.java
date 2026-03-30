public class CloseDoorButton extends Button {
    private final InternalPanel panel;

    public CloseDoorButton(InternalPanel panel) {
        super("CLOSE_DOOR");
        this.panel = panel;
    }

    @Override
    public void press() {
        panel.closeDoor();
    }
}