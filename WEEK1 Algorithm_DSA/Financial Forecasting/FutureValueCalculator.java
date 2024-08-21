public class FutureValueCalculator {
    // Recursive method to calculate the future value
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        } else {
            return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
        }
    }

    public static void main(String[] args) {
        double presentValue = 1000.0; // Initial investment
        double growthRate = 0.05; // 5% annual growth rate
        int years = 10; // Number of years to calculate the future value

        double futureValue = calculateFutureValue(presentValue, growthRate, years);

        System.out.println("Future Value: " + String.format("%.2f", futureValue));
    }
}