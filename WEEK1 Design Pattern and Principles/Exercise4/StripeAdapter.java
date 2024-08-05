public class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    // Constructor
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        stripeGateway.charge(amount);
    }
}
