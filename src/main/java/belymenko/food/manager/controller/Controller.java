package belymenko.food.manager.controller;

import belymenko.food.manager.domain.Category;
import belymenko.food.manager.domain.Drink;
import belymenko.food.manager.domain.Food;
import belymenko.food.manager.domain.Purchase;
import belymenko.food.manager.service.CategoryService;
import belymenko.food.manager.service.PurchaseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Viacheslav_Belymenko on 2/23/2017.
 */
public class Controller {

    private PurchaseService purchaseService;
    private CategoryService categoryService;

    public Controller(PurchaseService purchaseService, CategoryService categoryService) {
        this.purchaseService = purchaseService;
        this.categoryService = categoryService;
    }

    public void startController() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int command;
        boolean flag = false;
        while (!flag) {
            System.out.println("1 - Show all purchase");
            System.out.println("2 - Show drink categories");
            System.out.println("3 - Show food categories");
            System.out.println("4 - Add new category");
            System.out.println("5 - Add new purchase");
            System.out.println("6 - Show purchases between dates");
            System.out.println("7 - Show product expenses");
            System.out.println("0 - Exit");
            System.out.print("-> ");
            command = readInt(br);
            switch (command) {
                case 1:
                    System.out.println("~~~~ALL~PURCHASE~~~~");
                    Set<Map.Entry<Date, Purchase>> purchases = purchaseService.getPurchases().entrySet();
                    showPurchases(purchases);
                    break;
                case 2:
                    System.out.println("~~~~~~~DRINK~~~~~~~");
                    categoryService.getDrinkCategories().forEach(category -> System.out.println(category.getCategory()));
                    break;
                case 3:
                    System.out.println("~~~~~~~~FOOD~~~~~~~~");
                    categoryService.getFoodCategories().forEach(category -> System.out.println(category.getCategory()));
                    break;
                case 4:
                    System.out.println("~~ADD~NEW~CATEGORY~~");
                    addNewCategory();
                    break;
                case 5:
                    System.out.println("~~~~ADD~~PURCHASE~~~~");
                    addNewPurchase();
                    break;
                case 6:
                    System.out.println("~~SHOW~BETWEEN~DATES~~");
                    showBetweenDates();
                    break;
                case 7:
                    showProductExpenses();
                    break;
                case 0:
                    System.out.println("Exit");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Error input values");
                    break;
            }
        }

    }

    private void showPurchases(Set<Map.Entry<Date, Purchase>> purchases) {
        for (Map.Entry<Date, Purchase> entry : purchases) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(entry.getKey()) + " - " + entry.getValue());
        }
    }

    private void addNewCategory() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int command;
        boolean flag = false;
        while (!flag) {
            System.out.println("1 - Add drink categories");
            System.out.println("2 - Add food categories");
            System.out.println("0 - Cancel");
            System.out.print("-> ");
            command = readInt(br);
            switch (command) {
                case 1:
                    createCategory(true);
                    flag = true;
                    break;
                case 2:
                    createCategory(false);
                    flag = true;
                    break;
                case 0:
                    flag = true;
                    break;
                default:
                    System.out.println("Error input values");
                    break;
            }
        }
    }

    private void createCategory(boolean drink) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input category -> ");
        String category = sc.nextLine();
        if (drink) {
            categoryService.addDrinkCategory(category);
        } else {
            categoryService.addFoodCategory(category);
        }
    }

    private void addNewPurchase() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input product name-> ");
        String name = readString(br);
        System.out.print("Input product price-> ");
        Double price = readDouble(br);
        System.out.println("1 - Drink categories");
        System.out.println("2 - Food categories");
        System.out.print("Choose product category ->");
        int command = readInt(br);
        if (command == 2) {
            System.out.println("Select from food category");
            List<Category> foodCategories = categoryService.getFoodCategories();
            foodCategories.forEach(p -> System.out.println(foodCategories.indexOf(p) + "  -  " + p.getCategory()));
            System.out.print("-> ");
            int categoryIndex = readInt(br);
            Category category = new Category();
            try {
                category = foodCategories.get(categoryIndex);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("You select incorrect category!! Pleas try again");
                addNewPurchase();
            }
            System.out.print("Input food weight-> ");

            purchaseService.add(new Food(price, name, category, readDouble(br)));
        } else {
            System.out.println("Select from drink category");
            List<Category> drinkCategories = categoryService.getDrinkCategories();
            drinkCategories.forEach(p -> System.out.println(drinkCategories.indexOf(p) + "  -  " + p.getCategory()));
            System.out.print("-> ");
            int categoryIndex = readInt(br);
            Category category = new Category();
            try {
                category = drinkCategories.get(categoryIndex);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("You select incorrect category!! Pleas try again");
                addNewPurchase();
            }
            System.out.print("Input drink volume-> ");
            purchaseService.add(new Drink(price, name, category, readDouble(br)));
        }
    }

    private void showBetweenDates() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input start date like this 1999-12-12-> ");
        String from = sc.nextLine();
        System.out.print("Input end date like this 1999-12-12-> ");
        String to = sc.nextLine();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("\nStart date: " + from);
        try {
            showPurchases(purchaseService.getPurchasesBetweenDates(df.parse(from), df.parse(to)).entrySet());
        } catch (ParseException e) {
            System.out.println("You input incorrect date! Pleas try yet...");
            showBetweenDates();
        }
        System.out.println("End date: " + to);

    }

    private void showProductExpenses() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose order:");
        System.out.println("1 - From smallest to largest");
        System.out.println("2 - From largest to smallest");
        System.out.print("-> ");
        int desc = sc.nextInt();
        System.out.println("~~PRODUCT~~EXPENSES~~");
        purchaseService.productExpenses(desc == 1).forEach(exp -> System.out.println(exp.getKey().getCategory() + " - " + exp.getValue()));
    }

    private int readInt(BufferedReader br) {
        int value = 0;
        try {
            value = Integer.parseInt(br.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("You input incorrect number! Pleas try again...");
            System.out.print("-> ");
            value = readInt(br);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }

    private double readDouble(BufferedReader br) {
        double value = 0;
        try {
            value = Double.parseDouble(br.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("You input incorrect number! Pleas try again...");
            System.out.print("-> ");
            value = readDouble(br);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }

    private String readString(BufferedReader br) {
        String value = "";
        try {
            value = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
