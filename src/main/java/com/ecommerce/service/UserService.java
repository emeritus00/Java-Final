package com.ecommerce.service;
import com.ecommerce.dao.UserDAO;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.util.PasswordUtil;

import java.util.List;

/**
 * Service class for managing users in the system.
 * Handles user registration, login, and administrative tasks.
 */

public class UserService {
    private UserDAO userDAO = new UserDAO();

    /**
     * Registers a new user in the system.
     *
     * @param username the username of the new user.
     * @param email    the email address of the new user.
     * @param password the plain-text password of the new user.
     * @param role     the role of the new user (e.g., admin, buyer, seller).
     * @return {@code true} if registration is successful, {@code false} otherwise.
     */

    public boolean registerUser(String username, String email, String password, String role) {
        if (userDAO.getUserByEmail(email) != null) {
            return false; // User already exists
        }

        String hashedPassword = PasswordUtil.hashPassword(password);
        User user = new User() {
            @Override
            public void displayMenu() {

            }
        };
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setRole(role);
        userDAO.registerUser(user);
        return true;
    }

    /**
     * login in by already registered user in the system.
     * @param email    the email address of the user.
     * @param password the plain-text password of the new user.
     */

    public User login(String email, String password) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(email);

        if (user != null) {
            switch (user.getRole().toLowerCase()) {
                case "buyer":
                    return new Buyer(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
                case "seller":
                    return new Seller(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
                case "admin":
                    return new Admin(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
                default:
                    return null; // Invalid role
            }
        }
        return null; // User not found or password mismatch
    }

    /**
     * delete a user if found.
     *
     * @param userId of the user to delete.
     */

    public void deleteUser(int userId) {
        boolean isDeleted = userDAO.deleteUser(userId);
        if (isDeleted) {
            System.out.println("User with ID " + userId + " has been successfully deleted.");
        } else {
            System.out.println("Failed to delete user. User with ID " + userId + " may not exist.");
        }
    }

    /**
     * view all users and displays them if found.
     */

    public void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("\nList of Users:");
            for (User user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Role: " + user.getRole());
                System.out.println("-----------------------------");
            }
        }
    }

}
