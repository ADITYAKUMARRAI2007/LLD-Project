public class RefundService {
    private final PaymentService paymentService;

    public RefundService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean processRefund(Booking booking) {
        if (booking.getPayment() == null) {
            return false;
        }
        return paymentService.refund(booking.getPayment());
    }
}