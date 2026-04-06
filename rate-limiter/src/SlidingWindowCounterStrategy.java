import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class SlidingWindowCounterStrategy implements RateLimitingStrategy {
    private final Clock clock;
    private final int bucketCount;
    private final ConcurrentHashMap<String, Object> keyLocks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<AlgorithmCounterKey, SlidingWindowState> states = new ConcurrentHashMap<>();

    public SlidingWindowCounterStrategy() {
        this(Clock.systemUTC(), 10);
    }

    public SlidingWindowCounterStrategy(Clock clock, int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount must be > 0");
        }
        this.clock = clock;
        this.bucketCount = bucketCount;
    }

    @Override
    public RateLimitDecision tryAcquire(String rateLimitKey, RateLimitPolicy policy) {
        long now = clock.millis();
        Object lock = keyLocks.computeIfAbsent(rateLimitKey, ignored -> new Object());

        synchronized (lock) {
            List<SlidingWindowState> plan = new ArrayList<>();

            for (RateLimitRule rule : policy.getRules()) {
                AlgorithmCounterKey stateKey = new AlgorithmCounterKey(rateLimitKey, rule.getName());
                SlidingWindowState state = states.computeIfAbsent(stateKey,
                        ignored -> new SlidingWindowState(rule.getWindowMillis(), bucketCount));

                if (!state.canAcquire(now, rule.getLimit())) {
                    return RateLimitDecision.denied("Blocked by sliding window rule: " + rule.getName());
                }

                plan.add(state);
            }

            for (SlidingWindowState state : plan) {
                state.acquire(now);
            }
        }

        return RateLimitDecision.allowed("Allowed by sliding window strategy");
    }

    private static final class SlidingWindowState {
        private final long windowMillis;
        private final int bucketCount;
        private final long bucketSizeMillis;
        private final long[] bucketEpoch;
        private final long[] bucketCounts;

        SlidingWindowState(long windowMillis, int bucketCount) {
            this.windowMillis = windowMillis;
            this.bucketCount = bucketCount;
            this.bucketSizeMillis = Math.max(1, windowMillis / bucketCount);
            this.bucketEpoch = new long[bucketCount];
            this.bucketCounts = new long[bucketCount];
            for (int i = 0; i < bucketCount; i++) {
                bucketEpoch[i] = Long.MIN_VALUE;
            }
        }

        synchronized boolean canAcquire(long now, long limit) {
            rotateCurrentBucketIfNeeded(now);
            return totalInActiveWindow(now) < limit;
        }

        synchronized void acquire(long now) {
            rotateCurrentBucketIfNeeded(now);
            long currentBucketEpoch = now / bucketSizeMillis;
            int currentIndex = (int) (currentBucketEpoch % bucketCount);
            bucketCounts[currentIndex]++;
        }

        private void rotateCurrentBucketIfNeeded(long now) {
            long currentBucketEpoch = now / bucketSizeMillis;
            int currentIndex = (int) (currentBucketEpoch % bucketCount);

            if (bucketEpoch[currentIndex] != currentBucketEpoch) {
                bucketEpoch[currentIndex] = currentBucketEpoch;
                bucketCounts[currentIndex] = 0;
            }
        }

        private long totalInActiveWindow(long now) {
            long validFrom = now - windowMillis + 1;
            long total = 0;

            for (int i = 0; i < bucketCount; i++) {
                if (bucketEpoch[i] == Long.MIN_VALUE) {
                    continue;
                }
                long bucketStart = bucketEpoch[i] * bucketSizeMillis;
                long bucketEnd = bucketStart + bucketSizeMillis - 1;
                if (bucketEnd >= validFrom) {
                    total += bucketCounts[i];
                }
            }
            return total;
        }
    }
}
