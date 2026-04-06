public final class RateLimitingStrategyFactory {
    public RateLimitingStrategy create(RateLimitingAlgorithmType algorithmType) {
        if (algorithmType == null) {
            throw new IllegalArgumentException("algorithmType must not be null");
        }

        switch (algorithmType) {
            case FIXED_WINDOW:
                return new FixedWindowCounterStrategy();
            case SLIDING_WINDOW:
                return new SlidingWindowCounterStrategy();
            default:
                throw new IllegalArgumentException("Unsupported algorithm type: " + algorithmType);
        }
    }
}
