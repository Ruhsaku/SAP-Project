package com.racooncoding.perfumestore.regexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRegexes {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX_EMPLOYEE =
            Pattern.compile("^[A-Z0-9._%+-]+@(?i)\\bper-store.com\\b", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX_CUSTOMER =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_USERNAME =
            Pattern.compile("[A-Z0-9]{3,}", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD =
            Pattern.compile("[A-Z0-9._%+-]{6,}", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmployeeEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX_EMPLOYEE.matcher(emailStr);
        return matcher.matches();
    }
    public static boolean validateCustomerEmail(String emailStr){
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX_CUSTOMER.matcher(emailStr);
        return matcher.matches();
    }
    public static boolean validateUsername(String username){
        Matcher matcher = VALID_USERNAME.matcher(username);
        return matcher.matches();
    }
    public static boolean validPassword(String password){
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.matches();
    }

    public RegisterRegexes() {
    }
}
