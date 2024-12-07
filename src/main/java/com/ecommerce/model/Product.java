package com.ecommerce.model;

import java.math.BigDecimal;

/**
 * Represents a product with details such as ID, name, price, quantity, and seller ID.
 */

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private int sellerId;

    /**
     * Gets the product ID.
     *
     * @return The product ID.
     */
    public int getId() { return id; }

    /**
     * Sets the product ID.
     *
     * @param id The product ID.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the product name.
     *
     * @return The product name.
     */
    public String getName() { return name; }

    /**
     * Sets the product name.
     *
     * @param name The product name.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the product price.
     *
     * @return The product price.
     */
    public BigDecimal getPrice() { return price; }

    /**
     * Sets the product price.
     *
     * @param price The product price.
     */
    public void setPrice(BigDecimal price) { this.price = price; }

    /**
     * Gets the product quantity.
     *
     * @return The product quantity.
     */
    public int getQuantity() { return quantity; }

    /**
     * Sets the product quantity.
     *
     * @param quantity The product quantity.
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Gets the seller ID for the product.
     *
     * @return The seller ID.
     */
    public int getSellerId() { return sellerId; }

    /**
     * Sets the seller ID for the product.
     *
     * @param sellerId The seller ID.
     */
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }

    /**
     * Provides a string representation of the product.
     *
     * @return A string containing product details.
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price +
                ", quantity=" + quantity + ", sellerId=" + sellerId + "]";
    }
}

