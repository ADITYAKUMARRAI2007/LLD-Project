public class AttendanceScanner implements PowerSwitch, AttendanceScannable {

    @Override
    public void powerOn() { }

    @Override
    public void powerOff() { }

    @Override
    public int scanAttendance() {
        return 3;
    }
}