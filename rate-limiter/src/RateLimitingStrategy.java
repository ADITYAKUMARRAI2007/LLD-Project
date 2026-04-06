public interface RateLimitingStrategy {
    RateLimitDecision tryAcquire(String rateLimitKey, RateLimitPolicy policy);
}
