import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeMenu {

    private static final String FILENAME = "products.bin";
    private static final Object productLock = new Object();


    public static void EmployeeMenu(Scanner sc, PrintStream out) {
        boolean status = false;
        while (!status) {
            displayMenu(out);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    synchronized (productLock) {
                        Product new_product = Product.createProduct(sc);
                        List<Product> product_list = loadProducts();
                        for(Product p: product_list){
                            if(Objects.equals(p.getName(), new_product.getName())){
                                out.println("Product already in the store.");
                            } else {
                                product_list.add(new_product);
                                saveProducts(product_list);
                            }
                        }
                    }
                    break;
                case 2:
                    synchronized (productLock){
                        List<Product> product_list = loadProducts();
                        out.println("Enter the name of the product you want to edit: ");
                        String searched_product = sc.nextLine();
                        for(Product p : product_list){
                            if(Objects.equals(p.getName(), searched_product)){
                                Product selected_product = (Product) p;
                                out.println("Enter the thing u want to edit!");
                                out.println("name/price/quantity");
                                String option = sc.nextLine();
                                switch (option){
                                    case "name":
                                        out.println("Enter new name: ");
                                        String new_name = sc.nextLine();
                                        selected_product.setName(new_name);
                                        saveProducts(product_list);
                                        break;
                                    case "price":
                                        out.println("Enter new price: ");
                                        Double new_price = sc.nextDouble();
                                        selected_product.setPrice(new_price);
                                        saveProducts(product_list);
                                        break;
                                    case "quantity":
                                        out.println("Enter new quantity: ");
                                        Integer new_quantity = sc.nextInt();
                                        selected_product.setQuantity(new_quantity);
                                        saveProducts(product_list);
                                        break;
                                }
                            } else {
                                out.println("Product not found!");
                            }
                        }
                    }
                    break;
                case 3:
                    synchronized(productLock){
                        List<Product> product_list = loadProducts();
                        out.println("Enter the name of the product you want to discount: ");
                        String searched_product = sc.nextLine();
                        for(Product p : product_list){
                            if(p.getName() == searched_product) {
                                Product selected_product = (Product) p;
                                Boolean flag = false;
                                while(!flag){
                                    out.println("Enter what percent you want the item to be discounted: ");
                                    Integer percent = sc.nextInt();
                                    if(selected_product.getMin_price() > ((double) percent /100)*selected_product.getPrice()){
                                        out.println("Too big of a discount, try different percent!");
                                    } else {
                                        selected_product.setPrice(((double) percent /100)*selected_product.getPrice());
                                        saveProducts(product_list);
                                        flag = true;
                                    }
                                }
                            } else {
                                out.println("Product not found!");
                            }
                        }
                    }
                    break;
                case 4:
                    synchronized(productLock){
                        List<Product> product_list = loadProducts();
                        out.println("Enter the name of the product you want to be deleted: ");
                        String searched_product = sc.nextLine();
                        for(Product p : product_list){
                            if(Objects.equals(p.getName(), searched_product)) {
                                product_list.remove(p);
                                saveProducts(product_list);
                            } else {
                                out.println("Product not found!");
                            }
                        }
                    }
                    break;
                case 5:
                    synchronized(productLock){
                        List<Product> product_list = loadProducts();
                        Collections.sort(product_list, Comparator.comparingInt(Product::getProduct_id));
                        int i = 1;
                        for(Product p : product_list){
                            p.displayProduct();
                        }
                    }
                    break;
                case 6:
                    break;
                case 7:
                    out.println("Enter the minimum discount percentage (0-100): ");
                    int minDiscount = sc.nextInt();
                    out.println("Enter the maximum discount percentage (0-100): ");
                    int maxDiscount = sc.nextInt();
                    synchronized (productLock) {
                        List<Product> productList = loadProducts();
                        List<Product> saleProducts = productList.stream()
                                .filter(product -> product.getPrice() > product.getMin_price()) // Filter products with room for discount
                                .collect(Collectors.toList());

                        for (Product product : saleProducts) {
                            int randomDiscount = new Random().nextInt(maxDiscount - minDiscount + 1) + minDiscount;
                            product.setPrice(product.getPrice() * (1 - (double) randomDiscount / 100));
                        }
                        saveProducts(productList);
                        out.println("Massive sale initiated! Discounted products saved.");
                    }
                    break;
                case 8:
                    synchronized (productLock) {
                        List<Product> productList = loadProducts();
                        for (Product product : productList) {
                            product.setPrice(product.getMin_price()); // Set price back to minimum
                        }
                        saveProducts(productList);
                        out.println("Massive sale prices reverted to normal.");
                    }
                    break;
                case 9:
                    status = true;
                    break;

                default:
                    out.println("Invalid choice");
            }
        }
    }

    public static List<Product> loadProducts(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveProducts(List<Product> users){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            os.writeObject(users);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void displayMenu(PrintStream out) {
        out.println("=================================");
        out.println("1. Create Product");
        out.println("2. Edit Product");
        out.println("3. Set discount");
        out.println("4. Delete Product");
        out.println("5. Display all Products");
        out.println("6. Checking the balance for a certain period");
        out.println("7. Start an massive sale");
        out.println("8. Reverting prices back to normal");
        out.println("9. Exit");
        out.println("==================================");
        out.print("Enter your choice: ");
    }
}
//import java.io.*;
//        import java.sql.Connection;
//        import java.sql.Date;
//        import java.sql.DriverManager;
//// Import additional classes for connecting to your specific database
//        import java.sql.PreparedStatement;
//        import java.sql.ResultSet;
//        import java.sql.SQLException;
//        import java.util.*;
//        import java.util.stream.Collectors; // For filtering products in massive sale
//
//public class EmployeeMenu {
//
//    private static final String FILENAME = "products.bin";
//    private static final Object productLock = new Object();
//
//    // Replace with your actual database connection details
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/sales_db"; // Replace with your database URL
//    private static final String USER = "username"; // Replace with your database username
//    private static final String PASS = "password"; // Replace with your database password
//
//    public static void EmployeeMenu(Scanner sc, PrintStream out) {
//        boolean status = false;
//        while (!status) {
//            displayMenu(out);
//            int choice = sc.nextInt();
//            switch (choice) {
//                case 1:
//                    // Functionality for creating a product (unchanged)
//                    // ... (existing logic)
//                    break;
//                case 2:
//                    // Functionality for editing a product (unchanged)
//                    // ... (existing logic)
//                    break;
//                case 3:
//                    // Functionality for setting a discount (unchanged)
//                    // ... (existing logic)
//                    break;
//                case 4:
//                    // Functionality for deleting a product (unchanged)
//                    // ... (existing logic)
//                    break;
//                case 5:
//                    // Functionality for displaying all products (unchanged)
//                    // ... (existing logic)
//                    break;
//                case 6:
//                    // Checking balance for a period
//                    out.println("Enter the start date (YYYY-MM-DD): ");
//                    String startDate = sc.nextLine();
//                    out.println("Enter the end date (YYYY-MM-DD): ");
//                    String endDate = sc.nextLine();
//
//                    double totalSales = calculateTotalSales(startDate, endDate);
//                    out.println("Total sales from " + startDate + " to " + endDate + ": $" + totalSales);
//                    break;
//                case 7:
//                    // Starting a massive sale
//                    // ... (existing logic)
//                    break;
//                case 8:
//                    // Revert massive sale prices
//                    // ... (existing logic)
//                    break;
//                case 9:
//                    status = true;
//                    break;
//
//                default:
//                    out.println("Invalid choice");
//            }
//        }
//    }
//
//    private static double calculateTotalSales(String startDate, String endDate) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        double totalSales = 0.0;
//
//        try {
//            // Replace with your specific logic for connecting to your database using JDBC
//            Class.forName(JDBC_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            String sql = "SELECT SUM(sale_amount) AS total_sales FROM sales WHERE sale_date BETWEEN ? AND ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDate(1, Date.valueOf(startDate));
//            preparedStatement.setDate(2, Date.valueOf(endDate));
//
//            resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                totalSales = resultSet.getDouble("total_sales");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error connecting to database or executing query.");
//        } finally {
//            // Close resources (connection, statement, result set)
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return totalSales;
//    }
