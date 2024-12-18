/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pizza.pizzaorderingsystem.observer;

import com.pizza.pizzaorderingsystem.model.Order;

/**
 *
 * @author Alpha
 */
public class SMSNotification implements Observer {

    @Override
    public void update(Order order) {
        System.out.println("SMS Notification: Order " + order.getId() + " is now " + order.getState());
    }
}