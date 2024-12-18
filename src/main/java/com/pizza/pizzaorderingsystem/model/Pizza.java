package com.pizza.pizzaorderingsystem.model;

/**
 *
 * @author Alpha
 */  
public class Pizza {
    private String size;
    private String crust;
    private String sauce;
    private String toppings;
    private String name;
    private double price;
    private int quantity;

    Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.crust = builder.crust;
        this.sauce = builder.sauce;
        this.toppings = builder.toppings;
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
    }

    public String getSize() {
        return size;
    }

    public String getCrust() {
        return crust;
    }

    public String getSauce() {
        return sauce;
    }

    public String getToppings() {
        return toppings;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getDescription() {
        return "A " + size + " pizza with " + crust + " crust, " + sauce + " sauce, and " + toppings + ", Quantity: " + quantity;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size='" + size + '\'' +
                ", crust='" + crust + '\'' +
                ", sauce='" + sauce + '\'' +
                ", toppings='" + toppings + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public static class PizzaBuilder {
        private String size;
        private String crust;
        private String sauce;
        private String toppings;
        private String name;
        private double price;
        private int quantity = 1;

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setCrust(String crust) {
            this.crust = crust;
            return this;
        }

        public PizzaBuilder setSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public PizzaBuilder setToppings(String toppings) {
            this.toppings = toppings;
            return this;
        }

        public PizzaBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PizzaBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public PizzaBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
         
        public Pizza build() {
            return new Pizza(this);
        }
    }
}

