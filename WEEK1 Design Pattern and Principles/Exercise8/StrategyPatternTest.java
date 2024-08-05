public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();


        PaymentStrategy creditCard = new CreditCardPayment("1234567890123456", "John Doe", "123", "12/23");
        context.setPaymentStrategy(creditCard);
        context.executePayment(100.0);


        PaymentStrategy payPal = new PayPalPayment("johndoe@example.com", "password123");
        context.setPaymentStrategy(payPal);
        context.executePayment(200.0);
    }
}
