public class Logger {
    // Step 2.1: Private static instance of the class
    private static Logger instance;

    // Step 2.2: Private constructor to prevent instantiation
    private Logger() {
        // Private constructor logic (if any)
    }

    // Step 2.3: Public static method to get the instance of the class
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Additional methods for logging
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
