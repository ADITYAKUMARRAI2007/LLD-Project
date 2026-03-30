public class UpButton extends Button {
    private final ExternalPanel panel;

    public UpButton(ExternalPanel panel) {
        super("UP");
        this.panel = panel;
    }

    @Override
    public void press() {
        panel.requestUp();
    }
}