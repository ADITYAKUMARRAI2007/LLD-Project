public class MinAttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public MinAttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public String validate(StudentProfile s) {
        if (s.attendancePct < minAttendance) return "attendance below " + minAttendance;
        return null;
    }
}
