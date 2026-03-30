public interface PaymentProcessor {
    boolean pay(Payment payment);
    boolean refund(Payment payment);
}