package com.pizza.pizzaorderingsystem.service;

import com.pizza.pizzaorderingsystem.model.Pizza;
import com.pizza.pizzaorderingsystem.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alpha
 */
public class UserService {
    private Map<String, User> users = new HashMap<>();

    public User createUser(String name, String email, String mobileNumber) {
        User user = new User(name, email, mobileNumber);
        users.put(email, user);
        return user;
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public void addFavoritePizza(User user, Pizza pizza) {
        user.addFavoritePizza(pizza);
    }
}