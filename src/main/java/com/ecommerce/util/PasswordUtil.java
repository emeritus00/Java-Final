package com.ecommerce.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for handling password hashing and validation.
 */

public class PasswordUtil {

    /**
     * Hashes a plain-text password using BCrypt.
     *
     * @param plainTextPassword the plain-text password to hash.
     * @return the hashed password.
     */

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Validates a plain-text password against a hashed password.
     *
     * @param plainTextPassword the plain-text password.
     * @param hashedPassword    the hashed password to compare against.
     * @return {@code true} if the password matches, {@code false} otherwise.
     */

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
