package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Main class for the Marketplace System application.
 * This class contains the entry point and handles user interactions
 * through a console-based menu system.
 */

public class Main {

    private static final UserService userService = new UserService();
    private static final ProductService productService = new ProductService();


    /**
     * The main method serves as the entry point to the application.
     * It displays the main menu and handles user choices for registration, login, and exit.
     *
     * @param args command-line arguments (not used in this application).
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Marketplace System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register(scanner);
                case 2 -> login(scanner);
                case 3 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Handles user registration by prompting for details such as username, email, password, and role.
     *
     * @param scanner the {@link Scanner} object for reading user input.
     */

    private static void register(Scanner scanner) {
        System.out.println("\nRegister New User:");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.println("Select Role:");
        System.out.println("1. Buyer");
        System.out.println("2. Seller");
        System.out.println("3. Admin");
        System.out.print("Enter your choice: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String role = switch (roleChoice) {
            case 1 -> "buyer";
            case 2 -> "seller";
            case 3 -> "admin";
            default -> null;
        };

        if (role == null) {
            System.out.println("Invalid role selected.");
            return;
        }

        boolean success = userService.registerUser(username, email, password, role);
        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed. Email might already exist.");
        }
    }

    /**
     * Handles user login by prompting for email and password.
     * Displays the appropriate menu for the logged-in user based on their role.
     *
     * @param scanner the {@link Scanner} object for reading user input.
     */

    private static void login(Scanner scanner) {
        System.out.println("\nLogin:");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = userService.login(email, password);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            displayUserMenu(user, scanner);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    /**
     * Displays the menu for the logged-in user and handles user actions
     * based on their role (Buyer, Seller, Admin).
     *
     * @param user    the logged-in {@link User}.
     * @param scanner the {@link Scanner} object for reading user input.
     */

    private static void displayUserMenu(User user, Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\nUser Menu:");

            if (user instanceof Buyer) {
                System.out.println("1. Browse Products");
                System.out.println("2. Search Product by Name");
                System.out.println("3. View Product Details");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                handleBuyerMenu((Buyer) user, choice, scanner);
                if (choice == 4) {
                    System.out.println("You have successfully logged out.");
                    loggedIn = false;
                }
            } else if (user instanceof Seller) {
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. View My Products");
                System.out.println("5. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                handleSellerMenu((Seller) user, choice, scanner);
                if (choice == 5) {
                    System.out.println("You have successfully logged out.");
                    loggedIn = false;
                }
            } else if (user instanceof Admin) {
                System.out.println("1. View All Users");
                System.out.println("2. Delete User");
                System.out.println("3. View All Products");
                System.out.println("4. Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                handleAdminMenu((Admin) user, choice, scanner);
                if (choice == 4) {
                    System.out.println("You have successfully logged out.");
                    loggedIn = false;
                }
            } else {
                System.out.println("Invalid user type.");
                loggedIn = false; // Exit the loop if user type is invalid
            }
        }
    }


    private static void handleBuyerMenu(Buyer buyer, int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> productService.browseProducts();
            case 2 -> {
                System.out.print("Enter product name to search: ");
                String productName = scanner.nextLine();
                productService.searchProduct(productName);
            }
            case 3 -> {
                System.out.print("Enter product ID to view details: ");
                int productId = scanner.nextInt();
                scanner.nextLine();
                productService.viewProductDetails(productId);
            }
            case 4 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid option. Try again.");
        }
    }

    private static void handleSellerMenu(Seller seller, int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> {
                System.out.print("Enter Product Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Product Price: ");
                BigDecimal price = BigDecimal.valueOf(scanner.nextDouble());
                System.out.print("Enter Product Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setSellerId(seller.getId());
                productService.addProduct(product);
            }
            case 2 -> {
                System.out.print("Enter Product ID to update: ");
                int productId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter New Product Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter New Product Price: ");
                double price = scanner.nextDouble();
                System.out.print("Enter New Product Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                Product product = new Product();
                product.setId(productId);
                product.setName(name);
                product.setPrice(BigDecimal.valueOf(price));
                product.setQuantity(quantity);
                productService.updateProduct(product);
            }
            case 3 -> {
                System.out.print("Enter Product ID to delete: ");
                int productId = scanner.nextInt();
                scanner.nextLine();
                productService.deleteProduct(productId);
            }
            case 4 -> productService.viewSellerProducts(seller.getId());
            case 5 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid option. Try again.");
        }
    }

    private static void handleAdminMenu(Admin admin, int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> userService.viewAllUsers();
            case 2 -> {
                System.out.print("Enter User ID to delete: ");
                int userId = scanner.nextInt();
                scanner.nextLine();
                userService.deleteUser(userId);
            }
            case 3 -> productService.viewAllProductsWithSellers();
            case 4 -> System.out.println("Logging out...");
            default -> System.out.println("Invalid option. Try again.");
        }
    }
}
