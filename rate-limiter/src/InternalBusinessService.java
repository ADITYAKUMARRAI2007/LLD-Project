public final class InternalBusinessService {
    private final ExternalCallRateLimiter rateLimiter;
    private final RateLimitPolicy policy;
    private final ExternalResourceClient externalResourceClient;

    public InternalBusinessService(ExternalCallRateLimiter rateLimiter,
                                   RateLimitPolicy policy,
                                   ExternalResourceClient externalResourceClient) {
        this.rateLimiter = rateLimiter;
        this.policy = policy;
        this.externalResourceClient = externalResourceClient;
    }

    public String handle(ClientRequest request) {
        String tenantId = request.getTenantId();

        // Business logic executes first and only then decides whether external call is needed.
        if (!request.isExternalCallRequired()) {
            return "No external call needed for tenant=" + tenantId + ". Rate limiter was skipped.";
        }

        RateLimitDecision decision = rateLimiter.tryAcquire(tenantId, policy);
        if (!decision.isAllowed()) {
            return "External call denied for tenant=" + tenantId + " reason=" + decision.getMessage();
        }

        return externalResourceClient.invoke(tenantId);
    }
}
