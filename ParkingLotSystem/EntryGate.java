public class EntryGate {
    private final String gateId;

    public EntryGate(String gateId) {
        if (gateId == null || gateId.isBlank()) {
            throw new IllegalArgumentException("Entry gate id cannot be null or blank.");
        }
        this.gateId = gateId;
    }

    public String getGateId() {
        return gateId;
    }

    @Override
    public String toString() {
        return "EntryGate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }
}