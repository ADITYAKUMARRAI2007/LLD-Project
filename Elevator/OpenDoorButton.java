public class OpenDoorButton extends Button {
    private final InternalPanel panel;

    public OpenDoorButton(InternalPanel panel) {
        super("OPEN_DOOR");
        this.panel = panel;
    }

    @Override
    public void press() {
        panel.openDoor();
    }
}