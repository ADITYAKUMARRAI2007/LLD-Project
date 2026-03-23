public class MinCgrRule implements EligibilityRule {
    private final double minCgr;

    public MinCgrRule(double minCgr) {
        this.minCgr = minCgr;
    }

    @Override
    public String validate(StudentProfile s) {
        if (s.cgr < minCgr) return String.format("CGR below %.1f", minCgr);
        return null;
    }
}
