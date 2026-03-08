public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {

    PowerSwitch pjPower = reg.getFirstByCapability(PowerSwitch.class);
    InputConnectable pjInput = reg.getFirstByCapability(InputConnectable.class);

    pjPower.powerOn();
    pjInput.connectInput("HDMI-1");

    BrightnessControllable lights = reg.getFirstByCapability(BrightnessControllable.class);
    lights.setBrightness(60);

    TemperatureControllable ac = reg.getFirstByCapability(TemperatureControllable.class);
    ac.setTemperatureC(24);

    AttendanceScannable scan = reg.getFirstByCapability(AttendanceScannable.class);
    System.out.println("Attendance scanned: present=" + scan.scanAttendance());
}

    public void endClass() {

    System.out.println("Shutdown sequence:");

    reg.getFirstByCapability(Projector.class).powerOff();
    reg.getFirstByCapability(LightsPanel.class).powerOff();
    reg.getFirstByCapability(AirConditioner.class).powerOff();
}
}
