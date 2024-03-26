import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private static final String FILENAME = "users.bin";
    private final Object usersLock;
    public Server()
    {
        usersLock = new Object();
    }
    public static void main(String[] args) {
        new Server().start();
    }
    public void start()
    {
        try(ServerSocket server = new ServerSocket(1111)) {
            ExecutorService pool = Executors.newFixedThreadPool(3);
            while (true) {

                Socket client = server.accept();

                Thread myThread = new Thread(()->
                {
                    System.out.println("Client accepted!");

                    try{
                        Scanner sc = new Scanner(client.getInputStream());
                        PrintStream out = new PrintStream(client.getOutputStream());
                        usersMenu(sc, out);
                    } catch(IOException e){
                        e.printStackTrace();
                    } catch (CredentialsException e) {
                        throw new RuntimeException(e);
                    }
                });
                pool.execute(myThread);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public List<User> loadUser(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
            return (List<User>) in.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public void saveUsers(List<User> users){
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            os.writeObject(users);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void registerUser(String userName, String password) throws CredentialsException {
        User newUser =CreateUser.createUser(userName, password);
        synchronized (usersLock) {
            List<User> users = loadUser();
            users.add(newUser);
            saveUsers(users);
        }
    }
    private User loginUser(String userName, String password){
        synchronized (usersLock){
            for (User user: loadUser()) {
                if (Objects.equals(user.getUsername(), userName) && Objects.equals(user.getPassword(), password)){
                    return user;
                }
            }
            return null;
        }

    }
    private void usersMenu(Scanner sc, PrintStream out) throws CredentialsException {
        User loggedInUser;
        while (true) {

            String entry = sc.nextLine();
            if (entry.equalsIgnoreCase("N")) {
                out.println("Enter email: ");
                String registration_email = sc.nextLine();
                out.println("Enter password: ");
                String registration_pass1 = sc.nextLine();
                out.println("Enter confirmation password: ");
                String registration_pass2 = sc.nextLine();

                if (Objects.equals(registration_pass1, registration_pass2)) {
                    registerUser(registration_email, registration_pass1);
                    out.println("Registration successful!");
                } else {
                    out.println("Passwords do not match. Please try again.");
                }
            } else if (entry.equalsIgnoreCase("Y")) {
                out.println("Enter email: ");
                String login_email = sc.nextLine();
                out.println("Enter password: ");
                String login_password = sc.nextLine();

                loggedInUser = loginUser(login_email, login_password);
                if (loggedInUser != null) {
                    out.println("Login successful!");
                    out.println("Welcome, " + loggedInUser.getUsername() + "!");
                    switch(loggedInUser.getUserType()){
                        case EMPLOYEE:
                            EmployeeMenu.EmployeeMenu(sc, out);
                            break;
                        case CUSTOMER:
                            break;
                    }
                } else {
                    out.println("Invalid email or password.");
                }
            } else {
                out.println("Invalid input. Please enter Y or N");
            }
        }
    }
}
