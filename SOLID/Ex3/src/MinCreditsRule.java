public class MinCreditsRule implements EligibilityRule {
    private final int minCredits;

    public MinCreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public String validate(StudentProfile s) {
        if (s.earnedCredits < minCredits) return "credits below " + minCredits;
        return null;
    }
}
