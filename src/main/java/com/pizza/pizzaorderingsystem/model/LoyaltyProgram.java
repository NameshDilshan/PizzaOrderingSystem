/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pizza.pizzaorderingsystem.model;

/**
 *
 * @author Alpha
 */
import java.util.HashMap;
import java.util.Map;

public class LoyaltyProgram {
    private Map<User, Integer> pointsMap;

    public LoyaltyProgram() {
        this.pointsMap = new HashMap<>();
    }

    public void addPoints(User user, double amount) {
        int points = (int) (amount / 10);
        pointsMap.put(user, pointsMap.getOrDefault(user, 0) + points);
    }

    public int getPoints(User user) {
        return pointsMap.getOrDefault(user, 0);
    }
}
