public class AdapterPatternTest {
    public static void main(String[] args) {
        // Instantiate the third-party payment gateways
        PayPalGateway payPalGateway = new PayPalGateway();
        StripeGateway stripeGateway = new StripeGateway();

        // Wrap the third-party gateways with adapters
        PaymentProcessor payPalAdapter = new PayPalAdapter(payPalGateway);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripeGateway);

        // Use the adapters to process payments
        payPalAdapter.processPayment(100.0);
        stripeAdapter.processPayment(200.0);
    }
}
