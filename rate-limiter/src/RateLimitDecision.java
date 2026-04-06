public final class RateLimitDecision {
    private final boolean allowed;
    private final String message;

    private RateLimitDecision(boolean allowed, String message) {
        this.allowed = allowed;
        this.message = message;
    }

    public static RateLimitDecision allowed(String message) {
        return new RateLimitDecision(true, message);
    }

    public static RateLimitDecision denied(String message) {
        return new RateLimitDecision(false, message);
    }

    public boolean isAllowed() {
        return allowed;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RateLimitDecision{" +
                "allowed=" + allowed +
                ", message='" + message + '\'' +
                '}';
    }
}
