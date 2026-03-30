import java.util.UUID;

public class PaymentService {

    public Payment makePayment(double amount, PaymentRequest paymentRequest) {
        Payment payment = new Payment(UUID.randomUUID().toString(), amount, paymentRequest.getPaymentMethod());
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(paymentRequest.getPaymentMethod());
        boolean success = processor.pay(payment);

        if (!success) {
            payment.setStatus(PaymentStatus.FAILED);
        }

        return payment;
    }

    public boolean refund(Payment payment) {
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(payment.getPaymentMethod());
        return processor.refund(payment);
    }
}