package Encapsulation_Exercise.P04PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInfo = scanner.nextLine().split(" ");
        String pizzaName = pizzaInfo[1];
        int numberOfTops = Integer.parseInt(pizzaInfo[2]);

        String[] doughInfo = scanner.nextLine().split(" ");
        String flourType = doughInfo[1];
        String bakingTechnique = doughInfo[2];
        double weight = Double.parseDouble(doughInfo[3]);

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfTops);
            Dough dough = new Dough(flourType, bakingTechnique, weight);
            pizza.setDough(dough);
            String command = scanner.nextLine();

            while (!"END".equals(command)) {
                String[] toppingInfo = scanner.nextLine().split(" ");
                String name = toppingInfo[1];
                double toppingWeight = Double.parseDouble(toppingInfo[2]);
                Topping topping = new Topping(name, toppingWeight);
                pizza.addTopping(topping);
                command = scanner.nextLine();
            }
            System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
            } catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
