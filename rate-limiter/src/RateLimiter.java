public final class RateLimiter implements ExternalCallRateLimiter {
    private volatile RateLimitingStrategy strategy;

    public RateLimiter(RateLimitingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy must not be null");
        }
        this.strategy = strategy;
    }

    @Override
    public RateLimitDecision tryAcquire(String rateLimitKey, RateLimitPolicy policy) {
        return strategy.tryAcquire(rateLimitKey, policy);
    }

    public void setStrategy(RateLimitingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy must not be null");
        }
        this.strategy = strategy;
    }
}
