package com.pizza.pizzaorderingsystem.service;

import com.pizza.pizzaorderingsystem.model.Order;
import com.pizza.pizzaorderingsystem.model.User;
import com.pizza.pizzaorderingsystem.observer.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alpha
 */
public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void placeOrder(Order order) {
        orders.add(order);
        notifyObservers(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getOrdersForUser(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().equals(user)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    private void notifyObservers(Order order) {
        for (Observer observer : observers) {
            observer.update(order);
        }
    }
}