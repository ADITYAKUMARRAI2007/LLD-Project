import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        RateLimitPolicy policy = RateLimitPolicy.builder()
                .addRule(new RateLimitRule("perMinute", 5, Duration.ofMinutes(1)))
                .addRule(new RateLimitRule("perHour", 1000, Duration.ofHours(1)))
                .build();

        ExternalResourceClient externalClient = new MockExternalResourceClient();
        RateLimitingStrategyFactory strategyFactory = new RateLimitingStrategyFactory();

        RateLimiter rateLimiter = new RateLimiter(
            strategyFactory.create(RateLimitingAlgorithmType.FIXED_WINDOW)
        );
        InternalBusinessService service = new InternalBusinessService(rateLimiter, policy, externalClient);

        System.out.println("=== Fixed Window ===");
        runScenario(service);

        rateLimiter.setStrategy(strategyFactory.create(RateLimitingAlgorithmType.SLIDING_WINDOW));
        System.out.println("\n=== Sliding Window ===");
        runScenario(service);
    }

    private static void runScenario(InternalBusinessService service) {
        String tenant = "T1";

        // 1) Business logic says no external call -> limiter is not consulted.
        System.out.println(service.handle(new ClientRequest(tenant, false)));

        // 2) External call required -> limiter is consulted.
        for (int i = 1; i <= 7; i++) {
            System.out.println("Attempt " + i + ": " + service.handle(new ClientRequest(tenant, true)));
        }
    }
}
