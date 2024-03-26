public class Customer extends User{
    public Customer(String password, String username) {
        super(password, username);
    }
    @Override
    public  userType getUserType() {
        return userType.CUSTOMER;

    }

}
