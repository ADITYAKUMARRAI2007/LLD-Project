public abstract class Exporter {
    // Contract:
    // - request must be non-null
    // - exporter may throw IllegalArgumentException if format constraints are violated
    public final ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("request cannot be null");
        return doExport(req);
    }

    public abstract ExportResult doExport(ExportRequest req);
}
