public class DownButton extends Button {
    private final ExternalPanel panel;

    public DownButton(ExternalPanel panel) {
        super("DOWN");
        this.panel = panel;
    }

    @Override
    public void press() {
        panel.requestDown();
    }
}