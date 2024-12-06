package com.ecommerce.model;

import com.ecommerce.dao.ProductDAO;

import java.util.List;

/**
 * Represents a buyer user who can browse, search, and view products.
 */
public class Buyer extends User {

    /**
     * Default constructor for the Buyer class.
     */
    public Buyer() {}

    /**
     * Constructs a Buyer object with specified details.
     *
     * @param id       The unique ID of the buyer.
     * @param username The username of the buyer.
     * @param password The password of the buyer.
     * @param email    The email of the buyer.
     */
    public Buyer(int id, String username, String password, String email) {
        super(id, username, password, email, "buyer");
    }

    /**
     * Displays the buyer menu options.
     */
    @Override
    public void displayMenu() {
        System.out.println("Buyer Menu:");
        System.out.println("1. Browse Products");
        System.out.println("2. Search for a Product");
        System.out.println("3. View Product Details");
        System.out.println("4. Logout");
    }

    /**
     * Displays a list of all available products.
     */
    public void browseProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();
        products.forEach(System.out::println);
    }

    /**
     * Searches for a product by its name.
     *
     * @param productName The name of the product to search for.
     */
    public void searchProduct(String productName) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductByName(productName);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Views the details of a specific product by its ID.
     *
     * @param productId The ID of the product to view.
     */
    public void viewProductDetails(int productId) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }
}

