/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pizza.pizzaorderingsystem.model;

/**
 *
 * @author Alpha
 */ 

/**
 * Abstract decorator for Pizza to extend functionality dynamically.
 */
public abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        super(new Pizza.PizzaBuilder()
                .setSize(pizza.getSize())
                .setCrust(pizza.getCrust())
                .setSauce(pizza.getSauce())
                .setToppings(pizza.getToppings())
                .setName(pizza.getName())
                .setPrice(pizza.getPrice())
        );
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }
}


// Example of a concrete decorator for extra cheese
class ExtraCheeseDecorator extends PizzaDecorator {
    public ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " with extra cheese";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 2.0; // Adding $2 for extra cheese
    }
}
