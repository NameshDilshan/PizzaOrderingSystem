package com.pizza.pizzaorderingsystem.observer;

import com.pizza.pizzaorderingsystem.model.Order;

/**
 *
 * @author Alpha
 */
public class EmailNotification implements Observer {

    @Override
    public void update(Order order) {
        System.out.println("Email Notification: Order " + order.getId() + " is now " + order.getState());
    }
}