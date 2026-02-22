public abstract class Exporter {
    // Contract:
    // - request must be non-null
    // - title/body nulls are normalized to empty strings
    // - exporter may throw IllegalArgumentException if format constraints are violated
    public final ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("request cannot be null");
        ExportRequest normalized = new ExportRequest(safe(req.title), safe(req.body));
        return doExport(normalized);
    }

    protected abstract ExportResult doExport(ExportRequest req);

    private static String safe(String s) {
        return s == null ? "" : s;
    }
}
