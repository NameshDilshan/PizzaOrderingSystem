package com.pizza.pizzaorderingsystem.service;

import com.pizza.pizzaorderingsystem.model.Pizza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alpha
 */
public class PizzaService { 
    private List<Pizza> pizzas = new ArrayList<>();
    private final List<String> sizes = Arrays.asList("Small", "Medium", "Large");
    private final List<String> crusts = Arrays.asList("Thin", "Thick", "Stuffed");
    private final List<String> sauces = Arrays.asList("Tomato", "BBQ", "Pesto");
    private final List<String> toppings = Arrays.asList("Cheese", "Pepperoni", "Mushrooms", "Olives", "Onions");

    public Pizza buildPizza(Scanner scanner) {
        System.out.println("\n=== Build Your Pizza ===");

        // Select Size
        System.out.println("Select size:");
        printOptions(sizes);
        String size = getValidatedChoice(scanner, sizes);

        // Select Crust
        System.out.println("Select crust:");
        printOptions(crusts);
        String crust = getValidatedChoice(scanner, crusts);

        // Select Sauce
        System.out.println("Select sauce:");
        printOptions(sauces);
        String sauce = getValidatedChoice(scanner, sauces);

        // Select Toppings
        System.out.println("Select toppings (choose one or more, separated by commas):");
        printOptions(toppings);
        String selectedToppings = getMultipleChoices(scanner, toppings);

        // Enter Name
        System.out.print("Enter pizza name: ");
        String name = scanner.nextLine();

        // Set Price
        System.out.print("Enter price: ");
        double price = validatePrice(scanner);

        // Build Pizza
        Pizza pizza = new Pizza.PizzaBuilder()
                .setName(name)
                .setSize(size)
                .setCrust(crust)
                .setSauce(sauce)
                .setToppings(selectedToppings)
                .setPrice(price)
                .build();

        pizzas.add(pizza); // Store pizza
        System.out.println("Pizza created successfully: " + pizza);
        return pizza;
    }

    public List<Pizza> getAllPizzas() {
        return pizzas;
    }

    // Utility: Display numbered options
    private void printOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    // Utility: Validate single choice input
    private String getValidatedChoice(Scanner scanner, List<String> options) {
        int choice;
        while (true) {
            System.out.print("Enter your choice (1-" + options.size() + "): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= options.size()) {
                return options.get(choice - 1);
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    // Utility: Validate multiple choice input
    private String getMultipleChoices(Scanner scanner, List<String> options) {
        while (true) {
            System.out.print("Enter the numbers of your choices (e.g., 1,3,4): ");
            String input = scanner.nextLine();
            String[] parts = input.split(",");
            StringBuilder selected = new StringBuilder();
            boolean valid = true;

            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part.trim());
                    if (num < 1 || num > options.size()) {
                        valid = false;
                        break;
                    }
                    selected.append(options.get(num - 1)).append(", ");
                } catch (NumberFormatException e) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return selected.substring(0, selected.length() - 2); // Remove trailing comma
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    // Utility: Validate price input
    private double validatePrice(Scanner scanner) {
        while (true) {
            try {
                double price = scanner.nextDouble();
                scanner.nextLine();
                if (price > 0) return price;
                System.out.println("Price must be positive. Try again.");
            } catch (Exception e) {
                System.out.println("Invalid price. Enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
