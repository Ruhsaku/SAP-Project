import java.util.Scanner;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private double min_price;
    private int product_id;

    public Product(String name, double price, int quantity, double min_price, int product_id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.min_price = min_price;
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getMin_price() {
        return min_price;
    }

    public void setMin_price(double min_price) {
        this.min_price = min_price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void displayProduct() {
        System.out.println("Product id:" + product_id);
        System.out.println("Product: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: $" + getTotalPrice());
    }

    public static Product createProduct(Scanner sc){
        System.out.println("Enter name of product: ");
        String product_name = sc.nextLine();
        System.out.println("Enter the new price: ");
        Double product_price = sc.nextDouble();
        System.out.println("Enter quantity of product: ");
        Integer product_quantity = sc.nextInt();
        System.out.println("Set minimal price: ");
        Double product_min_price = sc.nextDouble();
        System.out.println("Set product id: ");
        Integer product_id = sc.nextInt();

        return new Product(product_name,product_price,product_quantity,product_min_price,product_id);
    }
}

