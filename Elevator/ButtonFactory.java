public class ButtonFactory {
    private ButtonFactory() {
    }

    public static UpButton createUpButton(ExternalPanel panel) {
        return new UpButton(panel);
    }

    public static DownButton createDownButton(ExternalPanel panel) {
        return new DownButton(panel);
    }

    public static OpenDoorButton createOpenDoorButton(InternalPanel panel) {
        return new OpenDoorButton(panel);
    }

    public static CloseDoorButton createCloseDoorButton(InternalPanel panel) {
        return new CloseDoorButton(panel);
    }

    public static AlarmButton createAlarmButton(InternalPanel panel) {
        return new AlarmButton(panel);
    }

    public static FloorButton createFloorButton(InternalPanel panel, int floor) {
        return new FloorButton(panel, floor);
    }
}