import java.util.UUID;

public class UpiPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean pay(Payment payment) {
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransactionReference("UPI-" + UUID.randomUUID());
        return true;
    }

    @Override
    public boolean refund(Payment payment) {
        payment.setStatus(PaymentStatus.REFUNDED);
        return true;
    }
}