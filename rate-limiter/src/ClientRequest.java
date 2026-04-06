public final class ClientRequest {
    private final String tenantId;
    private final boolean externalCallRequired;

    public ClientRequest(String tenantId, boolean externalCallRequired) {
        this.tenantId = tenantId;
        this.externalCallRequired = externalCallRequired;
    }

    public String getTenantId() {
        return tenantId;
    }

    public boolean isExternalCallRequired() {
        return externalCallRequired;
    }
}
