package com.ecommerce.service;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;
import java.util.List;

/**
 * Service class for managing products in the system.
 * Provides methods for common product-related operations.
 */

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Displays a list of all available products.
     */

    public void browseProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(product -> System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice()));
        }
    }

    /**
     * Searches for a product by name and displays it if found.
     *
     * @param name the name of the product to search for.
     */

    public void searchProduct(String name) {
        Product product = productDAO.getProductByName(name);
        if (product != null) {
            System.out.println("Found Product: " + product.getName() + " - $" + product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * view all products and displays them if found.
     *
     * @param productId of the products to view.
     */

    public void viewProductDetails(int productId) {
        Product product = productDAO.getProductById(productId);
        if (product != null) {
            System.out.println("Product Details:");
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        } else {
            System.out.println("Product not found.");
        }
    }


    /**
     * add product to products database.
     *
     * @param product details to add.
     */

    public void addProduct(Product product) {
        productDAO.addProduct(product);
        System.out.println("Product added successfully.");
    }

    /**
     * update product if found.
     *
     * @param product to update.
     */

    public void updateProduct(Product product) {
        boolean success = productDAO.updateProduct(product);
        if (success) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    /**
     * delete a product if found.
     *
     * @param productId of the product to delete.
     */

    public void deleteProduct(int productId) {
        boolean success = productDAO.deleteProduct(productId);
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    /**
     * view all sellers and displays them if found.
     *
     * @param sellerId of the products to view.
     */

    public void viewSellerProducts(int sellerId) {
        List<Product> products = productDAO.getProductsBySellerId(sellerId);
        if (products.isEmpty()) {
            System.out.println("No products found for the seller.");
        } else {
            products.forEach(product -> System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice()));
        }
    }
    /**
     * view all products with sellers.
     *
     */

    public void viewAllProductsWithSellers() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            products.forEach(product -> System.out.println(product.getId() + ": " + product.getName() + " - $" + product.getPrice() + " (Seller ID: " + product.getSellerId() + ")"));
        }
    }
}

