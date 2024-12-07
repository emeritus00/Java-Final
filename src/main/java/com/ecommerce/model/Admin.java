package com.ecommerce.model;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.UserDAO;

import java.util.List;

/**
 * Represents an admin user who has privileges to manage users and products.
 */
public class Admin extends User {

    /**
     * Default constructor for the Admin class.
     */
    public Admin() {}

    /**
     * Constructs an Admin object with specified details.
     *
     * @param id       The unique ID of the admin.
     * @param username The username of the admin.
     * @param password The password of the admin.
     * @param email    The email of the admin.
     */
    public Admin(int id, String username, String password, String email) {
        super(id, username, password, email, "admin");
    }

    /**
     * Displays the admin menu options.
     */
    @Override
    public void displayMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. View All Users");
        System.out.println("2. Delete User");
        System.out.println("3. View All Products");
        System.out.println("4. Logout");
    }
}

