import java.util.Objects;

final class AlgorithmCounterKey {
    private final String rateLimitKey;
    private final String ruleName;

    AlgorithmCounterKey(String rateLimitKey, String ruleName) {
        this.rateLimitKey = rateLimitKey;
        this.ruleName = ruleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlgorithmCounterKey)) {
            return false;
        }
        AlgorithmCounterKey that = (AlgorithmCounterKey) o;
        return Objects.equals(rateLimitKey, that.rateLimitKey) && Objects.equals(ruleName, that.ruleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateLimitKey, ruleName);
    }
}
