public class AlarmButton extends Button {
    private final InternalPanel panel;

    public AlarmButton(InternalPanel panel) {
        super("ALARM");
        this.panel = panel;
    }

    @Override
    public void press() {
        panel.triggerAlarm();
    }
}