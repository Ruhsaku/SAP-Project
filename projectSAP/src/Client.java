import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1111);
             Scanner sc = new Scanner(System.in);
             Scanner inputStream = new Scanner(socket.getInputStream());
             PrintStream outputStream = new PrintStream(socket.getOutputStream())) {

            System.out.println("Connected to server!");
            System.out.println("Do you have a registration2? Y/N");
            while (true) {

                String entry = sc.nextLine();
                outputStream.println(entry);

                String serverResponse = inputStream.nextLine();
                System.out.println(serverResponse);

                if (serverResponse.equalsIgnoreCase("Registration successful!") ||
                        serverResponse.equalsIgnoreCase("Login successful!")) {
                    break;
                }
            }

            while (true) {
                System.out.println("Enter your action:");
                String action = sc.nextLine();
                outputStream.println(action);

                String response = inputStream.nextLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
