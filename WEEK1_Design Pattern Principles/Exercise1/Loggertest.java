public class LoggerTest {
    public static void main(String[] args) {
        // Get the instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log some messages
        logger1.log("First message");
        logger2.log("Second message");

        // Check if both logger1 and logger2 are the same instance
        if (logger1 == logger2) {
            System.out.println("Logger is a singleton!");
        } else {
            System.out.println("Logger is not a singleton!");
        }
    }
}
