package com.ecommerce.model;

/**
 * Represents a seller user who can manage their own products.
 */
public class Seller extends User {

    /**
     * Default constructor for the Seller class.
     */

    public Seller() {}

    /**
     * Constructs a Seller object with specified details.
     *
     * @param id       The unique ID of the seller.
     * @param username The username of the seller.
     * @param password The password of the seller.
     * @param email    The email of the seller.
     */
    public Seller(int id, String username, String password, String email) {
        super(id, username, password, email, "seller");
    }

    /**
     * Displays the seller menu options.
     */
    @Override
    public void displayMenu() {
        System.out.println("Seller Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View My Products");
        System.out.println("5. Logout");
    }

}

