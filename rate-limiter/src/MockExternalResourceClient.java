public final class MockExternalResourceClient implements ExternalResourceClient {
    @Override
    public String invoke(String tenantId) {
        return "External resource call succeeded for tenant=" + tenantId;
    }
}
