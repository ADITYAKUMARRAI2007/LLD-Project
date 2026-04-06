import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RateLimitPolicy {
    private final List<RateLimitRule> rules;

    private RateLimitPolicy(List<RateLimitRule> rules) {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("At least one rule is required");
        }
        this.rules = Collections.unmodifiableList(new ArrayList<>(rules));
    }

    public static RateLimitPolicy of(List<RateLimitRule> rules) {
        return new RateLimitPolicy(rules);
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<RateLimitRule> getRules() {
        return rules;
    }

    public static final class Builder {
        private final List<RateLimitRule> rules = new ArrayList<>();

        public Builder addRule(RateLimitRule rule) {
            if (rule == null) {
                throw new IllegalArgumentException("rule must not be null");
            }
            rules.add(rule);
            return this;
        }

        public RateLimitPolicy build() {
            return new RateLimitPolicy(rules);
        }
    }
}
