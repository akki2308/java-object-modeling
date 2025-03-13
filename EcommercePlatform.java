import java.util.ArrayList;
import java.util.List;

// Product class
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Order class (Aggregation: Contains multiple products)
class Order {
    private static int orderCounter = 1000;
    private int orderId;
    private List<Product> products;
    private double totalAmount;

    public Order() {
        this.orderId = orderCounter++;
        this.products = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    // Add product to order
    public void addProduct(Product product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    // Display order details
    public void displayOrder() {
        System.out.println("\nOrder ID: " + orderId);
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println("   - " + product.getName() + " ($" + product.getPrice() + ")");
        }
        System.out.println("Total Amount: $" + totalAmount);
    }
}

// Customer class (Association: Can place multiple orders)
class Customer {
    private String name;
    private List<Order> orders;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    // Place an order
    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("\n" + name + " placed an order with ID: " + order);
    }

    // Display customer order history
    public void displayOrders() {
        System.out.println("\nCustomer: " + name);
        System.out.println("Order History:");
        if (orders.isEmpty()) {
            System.out.println("   No orders placed.");
        } else {
            for (Order order : orders) {
                order.displayOrder();
            }
        }
    }
}

// E-commerce Platform (Manages Customers, Orders, and Products)
class EcommercePlatform {
    public static void main(String[] args) {
        // Create Products
        Product product1 = new Product("Laptop", 850.99);
        Product product2 = new Product("Smartphone", 599.49);
        Product product3 = new Product("Headphones", 129.99);

        // Create Customer
        Customer customer1 = new Customer("Alice");

        // Create Order and add products
        Order order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product3);

        // Customer places the order
        customer1.placeOrder(order1);

        // Display Order History
        customer1.displayOrders();
    }
}
