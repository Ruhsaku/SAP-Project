import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUser {
    private final static Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{8,}$");
    private final static Pattern racooncodingEmailPattern = Pattern.compile("[a-z]+@racooncoding.com");
    private final static Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

    public static User createUser(String email, String password) throws CredentialsException {
        Matcher racooncodingMatcher = racooncodingEmailPattern.matcher(email);
        Matcher emailMatcher = emailPattern.matcher(email);

        if (racooncodingMatcher.matches()) {
            if (!passwordPattern.matcher(password).matches())
                throw new CredentialsException("Error: Invalid password format for an employee.");
            return new Employee(email, password);
        } else if (emailMatcher.matches()) {
            if (!passwordPattern.matcher(password).matches())
                throw new CredentialsException("Error: Invalid password format for a customer.");
            return new Customer(email, password);
        } else {
            throw new CredentialsException("Error: Invalid email format.");
        }
    }
}

//public class CreateUser {
//    private final static Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d).{8,}$\n");
//    public final static Pattern employeeEmailPattern = Pattern.compile("[a-z]+@racooncoding.com");
//    private final static Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\n");
//    public static User createUser(String userName, String password, userType userType) throws CredentialsException {
//
//        switch (userType){
//            case EMPLOYEE:
//            {
//                if (!employeeEmailPattern.matcher(userName).matches())
//                    throw new CredentialsException("Error: Invalid employee email format.");
//                if (!passwordPattern.matcher(password).matches())
//                    throw new CredentialsException("Error: Invalid password format.");
//                return new Employee(userName, password);
//            }
//            case CUSTOMER:
//            {
//                if (!emailPattern.matcher(userName).matches())
//                    throw new CredentialsException("Error: Invalid email format.");
//                if (password.length() < 5)
//                    throw new CredentialsException("Error: Invalid password format.");
//                return new Customer(userName, password);
//            }
//            default:
//                return null;
//        }
//    }
//}
//
//        userType type;
//        if(employeeEmailPattern.matcher(userName).matches()
//                && passwordPattern.matcher(password).matches()){
//            type = userType.EMPLOYEE;
//            return
//        }