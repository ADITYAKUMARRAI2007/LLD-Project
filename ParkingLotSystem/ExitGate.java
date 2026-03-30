public class ExitGate {
    private final String gateId;

    public ExitGate(String gateId) {
        if (gateId == null || gateId.isBlank()) {
            throw new IllegalArgumentException("Exit gate id cannot be null or blank.");
        }
        this.gateId = gateId;
    }

    public String getGateId() {
        return gateId;
    }

    @Override
    public String toString() {
        return "ExitGate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }
}