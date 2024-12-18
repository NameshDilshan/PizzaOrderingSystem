package com.pizza.pizzaorderingsystem.util;

/**
 *
 * @author Alpha
 */
public enum PaymentMethod {
    CREDIT_CARD,
    WALLET,
    CASH;

    public static PaymentMethod fromString(String method) throws IllegalArgumentException {
        return switch (method.toLowerCase()) {
            case "credit card" -> CREDIT_CARD;
            case "wallet" -> WALLET;
            case "cash" -> CASH;
            default -> throw new IllegalArgumentException("Invalid payment method");
        };
    }
}