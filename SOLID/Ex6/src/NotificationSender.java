public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }

    // Contract:
    // - notification object must be non-null
    // - sender may validate channel-specific destination and throw IllegalArgumentException
    public final void send(Notification n) {
        if (n == null) throw new IllegalArgumentException("notification cannot be null");
        doSend(n);
    }

    protected abstract void doSend(Notification n);
}
