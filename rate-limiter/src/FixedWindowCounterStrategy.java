import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class FixedWindowCounterStrategy implements RateLimitingStrategy {
    private final Clock clock;
    private final ConcurrentHashMap<String, Object> keyLocks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<AlgorithmCounterKey, FixedWindowState> states = new ConcurrentHashMap<>();

    public FixedWindowCounterStrategy() {
        this(Clock.systemUTC());
    }

    public FixedWindowCounterStrategy(Clock clock) {
        this.clock = clock;
    }

    @Override
    public RateLimitDecision tryAcquire(String rateLimitKey, RateLimitPolicy policy) {
        long now = clock.millis();
        Object lock = keyLocks.computeIfAbsent(rateLimitKey, ignored -> new Object());

        synchronized (lock) {
            List<FixedWindowPlanEntry> plan = new ArrayList<>();

            for (RateLimitRule rule : policy.getRules()) {
                AlgorithmCounterKey stateKey = new AlgorithmCounterKey(rateLimitKey, rule.getName());
                FixedWindowState state = states.computeIfAbsent(stateKey, ignored -> new FixedWindowState());

                if (!state.canAcquire(now, rule.getLimit(), rule.getWindowMillis())) {
                    return RateLimitDecision.denied("Blocked by fixed window rule: " + rule.getName());
                }

                plan.add(new FixedWindowPlanEntry(state, rule.getWindowMillis()));
            }

            for (FixedWindowPlanEntry entry : plan) {
                entry.state.acquire(now, entry.windowMillis);
            }
        }

        return RateLimitDecision.allowed("Allowed by fixed window strategy");
    }

    private static final class FixedWindowPlanEntry {
        private final FixedWindowState state;
        private final long windowMillis;

        private FixedWindowPlanEntry(FixedWindowState state, long windowMillis) {
            this.state = state;
            this.windowMillis = windowMillis;
        }
    }

    private static final class FixedWindowState {
        private long windowStartMillis = -1;
        private long count = 0;

        synchronized boolean canAcquire(long now, long limit, long windowMillis) {
            rotateWindowIfNeeded(now, windowMillis);
            return count < limit;
        }

        synchronized void acquire(long now, long windowMillis) {
            rotateWindowIfNeeded(now, windowMillis);
            count++;
        }

        private void rotateWindowIfNeeded(long now, long windowMillis) {
            long currentWindowStart = (now / windowMillis) * windowMillis;
            if (windowStartMillis != currentWindowStart) {
                windowStartMillis = currentWindowStart;
                count = 0;
            }
        }
    }
}
