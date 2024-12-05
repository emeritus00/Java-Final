package com.ecommerce.dao;
import com.ecommerce.model.Product;
import com.ecommerce.util.Database;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProductDAO class handles data operations for the Product model,
 * including CRUD operations and retrieval of products based on specific criteria.
 */
public class ProductDAO {

    /**
     * Adds a new product to the database.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of products for a specific seller.
     *
     * @param sellerId The ID of the seller.
     * @return A list of products belonging to the seller.
     */
    public List<Product> getProductsBySeller(int sellerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE seller_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(mapToProduct(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products by seller: " + e.getMessage());
        }
        return products;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param productId The ID of the product.
     * @return The product if found, or null if not found.
     */
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToProduct(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product by ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a product by its name.
     *
     * @param productName The name of the product.
     * @return The product if found, or null if not found.
     */
    public Product getProductByName(String productName) {
        String sql = "SELECT * FROM products WHERE name = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToProduct(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product by name: " + e.getMessage());
        }
        return null;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param product The product with updated details.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setBigDecimal(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param productId The ID of the product to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = Database.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(mapToProduct(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all products: " + e.getMessage());
        }
        return products;
    }

    /**
     * Maps a ResultSet row to a Product object.
     *
     * @param rs The ResultSet row.
     * @return The Product object.
     * @throws SQLException if an error occurs while accessing the ResultSet.
     */
    private Product mapToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setSellerId(rs.getInt("seller_id"));
        return product;
    }

    /**
     * Retrieves products for a specific seller by their ID.
     *
     * @param sellerId The ID of the seller.
     * @return A list of products belonging to the seller.
     */
    public List<Product> getProductsBySellerId(int sellerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE seller_id = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(mapToProduct(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products by seller: " + e.getMessage());
        }
        return products;
    }
}

