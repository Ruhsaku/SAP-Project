import java.io.*;
import java.util.List;

public class CustomerMenu {

    private static final String FILENAME = "cart.bin";
    private static final Object cartLock = new Object();



    public static List<Product> loadCart(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<Product>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void saveCart(List<Product> users){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            os.writeObject(users);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void displayMenu() {
        System.out.println("=================================");
        System.out.println("1. Add Product to cart");
        System.out.println("2. Remove Product from cart");
        System.out.println("3. Display all Product");
        System.out.println("4. Display all discounted products");
        System.out.println("5. Buy product");
        System.out.println("6. Exit");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }
}
