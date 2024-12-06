package com.ecommerce.dao;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Buyer;
import com.ecommerce.model.Seller;
import com.ecommerce.model.User;
import com.ecommerce.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing User-related database operations.
 * This class provides methods to perform CRUD operations for users.
 */
public class UserDAO {

    /**
     * Registers a new user in the database.
     *
     * @param user The user object containing the username, password, email, and role to be registered.
     */
    public void registerUser(User user) {
        String sql = "INSERT INTO Users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the database by their email address.
     *
     * @param email The email address of the user to be retrieved.
     * @return A User object if the user is found, or null if no user exists with the given email.
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User() {
                    @Override
                    public void displayMenu() {
                        // Implement as needed
                    }
                };
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of User objects, or an empty list if no users exist.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String role = resultSet.getString("role");
                User user = switch (role.toLowerCase()) {
                    case "buyer" -> new Buyer();
                    case "seller" -> new Seller();
                    case "admin" -> new Admin();
                    default -> null;
                };

                if (user != null) {
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(role);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all users: " + e.getMessage());
        }
        return users;
    }

    /**
     * Deletes a user from the database by their unique ID.
     * Ensures associated products are handled to avoid foreign key constraint violations.
     *
     * @param userId The ID of the user to be deleted.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(int userId) {
        String deleteProductsSql = "DELETE FROM products WHERE seller_id = ?";
        String deleteUserSql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Database.getConnection()) {
            connection.setAutoCommit(false); // Start transaction

            // Delete associated products
            try (PreparedStatement deleteProductsStmt = connection.prepareStatement(deleteProductsSql)) {
                deleteProductsStmt.setInt(1, userId);
                deleteProductsStmt.executeUpdate();
            }

            // Delete the user
            try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserSql)) {
                deleteUserStmt.setInt(1, userId);
                int rowsAffected = deleteUserStmt.executeUpdate();

                if (rowsAffected > 0) {
                    connection.commit(); // Commit transaction
                    return true;
                } else {
                    connection.rollback(); // Rollback if no user was deleted
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
        return false;
    }

}

