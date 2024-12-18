package com.pizza.pizzaorderingsystem.observer;

import com.pizza.pizzaorderingsystem.model.Order;

/**
 *
 * @author Alpha
 */
public interface Observer {
    void update(Order order);
}