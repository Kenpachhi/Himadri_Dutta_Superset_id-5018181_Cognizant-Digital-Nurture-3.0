import java.util.ArrayList;
import java.util.Scanner;


class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    
    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

public class InventoryManagement {
    private final ArrayList<Product> products;
    private final Scanner scanner;

    // Constructor
    public InventoryManagement() {
        this.products = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Method to add a product
    public void addProduct() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter product name: ");
        String productName = scanner.next();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Product product = new Product(productId, productName, quantity, price);
        products.add(product);
        System.out.println("Product added successfully!");
    }

    // Method to update a product
    public void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();

        for (Product product : products) {
            if (product.getProductId() == productId) {
                System.out.print("Enter new product name: ");
                String productName = scanner.next();
                System.out.print("Enter new quantity: ");
                int quantity = scanner.nextInt();
                System.out.print("Enter new price: ");
                double price = scanner.nextDouble();

                product.setProductName(productName);
                product.setQuantity(quantity);
                product.setPrice(price);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to delete a product
    public void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();

        for (Product product : products) {
            if (product.getProductId() == productId) {
                products.remove(product);
                System.out.println("Product deleted successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    // Method to display all products
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in the inventory!");
        } else {
            for (Product product : products) {
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Price: " + product.getPrice());
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. Display Products");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> inventory.addProduct();
                    case 2 -> inventory.updateProduct();
                    case 3 -> inventory.deleteProduct();
                    case 4 -> inventory.displayProducts();
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}