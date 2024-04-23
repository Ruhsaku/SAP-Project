package com.racooncoding.perfumestore.passwordhash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordDecoder {

    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            String hashedInputPassword = hexString.toString();
            return hashedInputPassword.equals(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error decoding password", e);
        }
    }
}

