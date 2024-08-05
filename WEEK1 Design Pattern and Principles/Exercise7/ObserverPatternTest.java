public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("MobileApp1");
        Observer webApp = new WebApp("WebApp1");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        System.out.println("Setting stock price to 100.00");
        stockMarket.setStockPrice(100.00);

        System.out.println("\nDeregistering MobileApp1");
        stockMarket.deregisterObserver(mobileApp);

        System.out.println("\nSetting stock price to 150.00");
        stockMarket.setStockPrice(150.00);
    }
}
