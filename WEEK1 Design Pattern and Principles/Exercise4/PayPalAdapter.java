public class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    // Constructor
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}
