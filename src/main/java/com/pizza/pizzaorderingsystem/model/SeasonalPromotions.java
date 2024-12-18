/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pizza.pizzaorderingsystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alpha
 */ 

public class SeasonalPromotions {
    private List<String> promotions;

    public SeasonalPromotions() {
        this.promotions = new ArrayList<>();
        promotions.add("Buy 1 Get 1 Free");
        promotions.add("20% Off on Weekends");
        promotions.add("Free Drink with Large Pizza");
    }

    public List<String> getPromotions() {
        return promotions;
    }
    
    public void addPromotion(String name, String description) {
        promotions.add(name + ": " + description);
    }
    
}
