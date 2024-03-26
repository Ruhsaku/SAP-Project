public class Employee extends User{
    public Employee(String password, String username) {
        super(password, username);
    }

    @Override
    public  userType getUserType() {
        return userType.EMPLOYEE;

    }


}
