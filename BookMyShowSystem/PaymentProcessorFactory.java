public class PaymentProcessorFactory {

    private PaymentProcessorFactory() {
    }

    public static PaymentProcessor getProcessor(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.UPI) {
            return new UpiPaymentProcessor();
        }
        if (paymentMethod == PaymentMethod.CARD) {
            return new CardPaymentProcessor();
        }
        return new NetBankingPaymentProcessor();
    }
}