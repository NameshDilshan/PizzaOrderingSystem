package com.pizza.pizzaorderingsystem;

import com.pizza.pizzaorderingsystem.model.LoyaltyProgram;
import com.pizza.pizzaorderingsystem.model.Order;
import com.pizza.pizzaorderingsystem.service.payment.PaymentStrategy;
import com.pizza.pizzaorderingsystem.model.Pizza;
import com.pizza.pizzaorderingsystem.model.SeasonalPromotions;
import com.pizza.pizzaorderingsystem.model.User;
import com.pizza.pizzaorderingsystem.observer.EmailNotification;
import com.pizza.pizzaorderingsystem.observer.SMSNotification;
import com.pizza.pizzaorderingsystem.service.OrderService;
import com.pizza.pizzaorderingsystem.service.PizzaService;
import com.pizza.pizzaorderingsystem.service.UserService;
import com.pizza.pizzaorderingsystem.service.payment.CashPayment;
import com.pizza.pizzaorderingsystem.service.payment.CreditCardPayment;
import com.pizza.pizzaorderingsystem.service.payment.WalletPayment;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Alpha
 */ 

public class PizzaOrderingSystem {
    public static void main(String[] args) {
        // Initialize Services
        UserService userService = new UserService();
        PizzaService pizzaService = new PizzaService();
        OrderService orderService = new OrderService();
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        SeasonalPromotions seasonalPromotions = new SeasonalPromotions();

        // Observer Pattern for Notifications
        orderService.addObserver(new EmailNotification());
        orderService.addObserver(new SMSNotification());

        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\n=== Pizza Ordering System ===");
            System.out.println("1. Create User");
            System.out.println("2. Create Custom Pizza");
            System.out.println("3. Save Pizza as Favorite");
            System.out.println("4. Place Order");
            System.out.println("5. Update Order Status");
            System.out.println("6. View Orders");
            System.out.println("7. View Favorite Pizzas");
            System.out.println("8. View Loyalty Points");
            System.out.println("9. View Seasonal Specials");
            System.out.println("10. Provide Feedback and Rating");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your mobile number: ");
                    String mobileNumber = scanner.nextLine();
                    currentUser = userService.createUser(name, email, mobileNumber);
                    System.out.println("User created: " + currentUser);
                    break;

                case 2:
                    if (currentUser == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    Pizza pizza = pizzaService.buildPizza(scanner);
                    System.out.println("Pizza created: " + pizza);
                    break;

                case 3:
                    if (currentUser == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }

                    List<Pizza> allPizzas = pizzaService.getAllPizzas();
                    if (allPizzas.isEmpty()) {
                        System.out.println("No pizzas available. Create one first (Option 2).");
                        break;
                    }

                    System.out.println("Select a pizza to add to favorites:");
                    for (int i = 0; i < allPizzas.size(); i++) {
                        System.out.println((i + 1) + ". " + allPizzas.get(i));
                    }

                    System.out.print("Enter the number of the pizza: ");
                    int choice1 = scanner.nextInt();
                    scanner.nextLine();

                    if (choice1 >= 1 && choice1 <= allPizzas.size()) {
                        Pizza selectedPizza = allPizzas.get(choice1 - 1);
                        userService.addFavoritePizza(currentUser, selectedPizza);
                        System.out.println("Pizza added to favorites: " + selectedPizza);
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                    break;

                case 4:
                    if (currentUser == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    if (currentUser.getFavoritePizzas().isEmpty()) {
                        System.out.println("You haven't selected a favorite pizza. Save one first (Option 3).\n");
                        break;
                    }

                    Pizza selectPizza = selectPizza(scanner, currentUser.getFavoritePizzas());
                    PaymentStrategy paymentMethod = selectPaymentMethod(scanner);

                    if (selectPizza != null && paymentMethod != null) {
                        Order order = new Order(selectPizza, currentUser, paymentMethod);
                        loyaltyProgram.addPoints(currentUser, selectPizza.getPrice());
                        orderService.placeOrder(order);
                        paymentMethod.pay(selectPizza.getPrice());
                        System.out.println("Order placed: " + order);
                    }
                    break;

                case 5:
                    updateOrderStatus(scanner, orderService);
                    break;

                case 6:
                    viewOrders(orderService, currentUser);
                    break;

                case 7:
                    viewFavorites(currentUser);
                    break;

                case 8:
                    if (currentUser == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    System.out.println("Your Loyalty Points: " + loyaltyProgram.getPoints(currentUser));
                    break;

                case 9:
                    System.out.println("Seasonal Specials and Promotions:");
                    seasonalPromotions.getPromotions().forEach(System.out::println);
                    break;

                case 10:
                    provideFeedback(scanner, orderService);
                    break;

                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Pizza selectPizza(Scanner scanner, List<Pizza> pizzas) {
        System.out.println("Select a pizza to order:");
        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println((i + 1) + ". " + pizzas.get(i));
        }

        System.out.print("Enter the number of the pizza: ");
        int pizzaChoice = scanner.nextInt();
        scanner.nextLine();

        if (pizzaChoice < 1 || pizzaChoice > pizzas.size()) {
            System.out.println("Invalid choice. Try again.");
            return null;
        }

        return pizzas.get(pizzaChoice - 1);
    }

    private static PaymentStrategy selectPaymentMethod(Scanner scanner) {
        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Wallet");
        System.out.println("3. Cash");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: return new CreditCardPayment();
            case 2: return new WalletPayment();
            case 3: return new CashPayment();
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    private static void updateOrderStatus(Scanner scanner, OrderService orderService) {
        System.out.print("Enter order ID to update status: ");
        String orderId = scanner.nextLine();

        System.out.println("Select new status:");
        System.out.println("1. Placed");
        System.out.println("2. Preparing");
        System.out.println("3. Delivered");
        System.out.print("Enter your choice (1-3): ");
        int statusChoice = scanner.nextInt();
        scanner.nextLine();

        orderService.getOrders().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .ifPresentOrElse(
                        orderToUpdate -> {
                            orderToUpdate.setState(statusChoice);
                            System.out.println("Order updated: " + orderToUpdate);
                        },
                        () -> System.out.println("Order not found.")
                );
    }

    private static void viewOrders(OrderService orderService, User currentUser) {
        if (currentUser == null) {
            System.out.println("Please create a user first.");
            return;
        }
        System.out.println("Your Orders:");
        orderService.getOrdersForUser(currentUser).forEach(System.out::println);
    }

    private static void viewFavorites(User currentUser) {
        if (currentUser == null) {
            System.out.println("Please create a user first.");
            return;
        }
        System.out.println("Your Favorite Pizzas:");
        currentUser.getFavoritePizzas().forEach(System.out::println);
    }

    private static void provideFeedback(Scanner scanner, OrderService orderService) {
        System.out.print("Enter order ID to provide feedback: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter your feedback: ");
        String feedback = scanner.nextLine();
        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        orderService.getOrders().stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .ifPresentOrElse(
                        orderToRate -> {
                            orderToRate.addFeedback(feedback, rating);
                            System.out.println("Feedback added: " + orderToRate.getFeedback());
                        },
                        () -> System.out.println("Order not found.")
                );
    }
}