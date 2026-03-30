public class PaymentRequest {
    private final PaymentMethod paymentMethod;

    public PaymentRequest(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}