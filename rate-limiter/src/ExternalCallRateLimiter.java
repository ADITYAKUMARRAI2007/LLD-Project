public interface ExternalCallRateLimiter {
    RateLimitDecision tryAcquire(String rateLimitKey, RateLimitPolicy policy);
}
