import java.time.Duration;

public final class RateLimitRule {
    private final String name;
    private final long limit;
    private final Duration window;

    public RateLimitRule(String name, long limit, Duration window) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Rule name must not be empty");
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be > 0");
        }
        if (window == null || window.isZero() || window.isNegative()) {
            throw new IllegalArgumentException("Window must be positive");
        }
        this.name = name;
        this.limit = limit;
        this.window = window;
    }

    public String getName() {
        return name;
    }

    public long getLimit() {
        return limit;
    }

    public Duration getWindow() {
        return window;
    }

    public long getWindowMillis() {
        return window.toMillis();
    }

    @Override
    public String toString() {
        return "RateLimitRule{" +
                "name='" + name + '\'' +
                ", limit=" + limit +
                ", window=" + window +
                '}';
    }
}
