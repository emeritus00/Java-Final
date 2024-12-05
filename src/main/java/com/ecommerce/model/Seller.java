package com.ecommerce.model;

import com.ecommerce.dao.ProductDAO;

import java.util.List;

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

    /**
     * Adds a new product.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(product);
        System.out.println("Product added successfully!");
    }

    /**
     * Updates an existing product.
     *
     * @param product The product to be updated.
     */
    public void updateProduct(Product product) {
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.updateProduct(product)) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to be deleted.
     */
    public void deleteProduct(int productId) {
        ProductDAO productDAO = new ProductDAO();
        if (productDAO.deleteProduct(productId)) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    /**
     * Views all products belonging to the seller.
     *
     * @param sellerId The unique ID of the seller.
     */
    public void viewMyProducts(int sellerId) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getProductsBySeller(sellerId);
        products.forEach(System.out::println);
    }
}

