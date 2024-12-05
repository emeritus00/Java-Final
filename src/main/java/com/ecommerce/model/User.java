package com.ecommerce.model;

/**
 * Abstract class representing a user in the system.
 * All user types (e.g., Admin, Buyer, Seller) extend this class.
 */

public abstract class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    /**
     * Default constructor for the User class.
     */
    public User() {}

    /**
     * Parameterized constructor to initialize a User object.
     *
     * @param id       the unique ID of the user.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param email    the email address of the user.
     * @param role     the role of the user (e.g., admin, buyer, seller).
     */
    public User(int id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    /**
     * Abstract method to display the menu for the user.
     * Implementations should provide role-specific menu options.
     */

    public abstract void displayMenu();
}
