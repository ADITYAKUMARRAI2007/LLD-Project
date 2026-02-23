import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
        this.rules = defaultRules(new RuleInput());
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        for (EligibilityRule rule : rules) {
            String reason = rule.validate(s);
            if (reason != null) {
                reasons.add(reason);
                break; 
            }
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }

    private static List<EligibilityRule> defaultRules(RuleInput input) {
        return List.of(
                new DisciplinaryRule(),
                new MinCgrRule(input.minCgr),
                new MinAttendanceRule(input.minAttendance),
                new MinCreditsRule(input.minCredits)
        );
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
