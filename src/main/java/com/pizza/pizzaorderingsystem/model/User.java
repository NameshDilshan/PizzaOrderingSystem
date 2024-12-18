package com.pizza.pizzaorderingsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alpha
 */  

public class User {
    private String name;
    private String email;
    private String mobileNumber;
    private List<Pizza> favoritePizzas;

    public User(String name, String email, String mobileNumber) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.favoritePizzas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public List<Pizza> getFavoritePizzas() {
        return favoritePizzas;
    }

    public void addFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', mobileNumber='" + mobileNumber + "'}";
    }
}
