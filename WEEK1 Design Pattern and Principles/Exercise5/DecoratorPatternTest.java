public class DecoratorPatternTest {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier.send("Initial notification");

        System.out.println("\nAdding SMS notification:");
        notifier = new SMSNotifierDecorator(notifier);
        notifier.send("New notification");

        System.out.println("\nAdding Slack notification:");
        notifier = new SlackNotifierDecorator(notifier);
        notifier.send("Another notification");
    }
}
