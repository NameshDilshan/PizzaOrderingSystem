package com.pizza.pizzaorderingsystem.model;

import com.pizza.pizzaorderingsystem.service.payment.PaymentStrategy;
import com.pizza.pizzaorderingsystem.util.PaymentMethod;
import java.util.UUID;

/**
 *
 * @author Alpha
 */ 
public class Order {
    private String id;
    private Pizza pizza;
    private User user;
    private PaymentStrategy paymentStrategy;
    private OrderState state;
    private String feedback;
    private int rating;

    public Order(Pizza pizza, User user, PaymentStrategy paymentStrategy) {
        this.id = String.valueOf((int) (Math.random() * 90000) + 10000);
        this.pizza = pizza;
        this.user = user;
        this.paymentStrategy = paymentStrategy;
        this.state = new PlacedState(this);
    }

    public String getId() {
        return id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public User getUser() {
        return user;
    }

    public String getState() {
        return state.getState();
    }

    public void setState(int stateNumber) {
        switch (stateNumber) {
            case 1:
                this.state = new PlacedState(this);
                break;
            case 2:
                this.state = new PreparingState(this);
                break;
            case 3:
                this.state = new DeliveredState(this);
                break;
            default:
                System.out.println("Invalid status number.");
        }
    }

    public void addFeedback(String feedback, int rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getFeedback() {
        return "Feedback: " + feedback + ", Rating: " + rating;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', pizza=" + pizza + ", state=" + state.getState() + '}';
    }
}

// State Interface
interface OrderState {
    String getState();
}

// Concrete States
class PlacedState implements OrderState {
    private Order order;

    public PlacedState(Order order) {
        this.order = order;
    }

    @Override
    public String getState() {
        return "Placed";
    }
}

class PreparingState implements OrderState {
    private Order order;

    public PreparingState(Order order) {
        this.order = order;
    }

    @Override
    public String getState() {
        return "Preparing";
    }
}

class DeliveredState implements OrderState {
    private Order order;

    public DeliveredState(Order order) {
        this.order = order;
    }

    @Override
    public String getState() {
        return "Delivered";
    }
}
