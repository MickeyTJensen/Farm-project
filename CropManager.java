package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    static ArrayList<Crop> crops;
    private IdGenerator cropIdGenerator;
    public CropManager() {
        crops = new ArrayList<>();
        crops.add(new Crop(cropIdGenerator.generateId(), "Wheat", "Grain", 1, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Corn", "Grain", 10, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Barley", "Grain", 5, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Oats", "Grain", 5, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Alfalfa", "Grass", 3, "Feed to: Horse, Cow, Sheep and Donkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Timothy Hay", "Grass", 4, "Feed to: Horse, Cow, Sheep and Donkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Carrots", "Vegetables", 6, "Feed to: Pig, and Rabbit"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Lettuce", "Vegetables", 12, "Feed to: Pig and Rabbit"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Soybeans", "Grain", 14, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Rye", "Grain", 8, "Feed to: Chicken, Duck and Turkey"));
        crops.add(new Crop(cropIdGenerator.generateId(), "Wheat", "Grain", 1, "Feed to: Chicken, Duck and Turkey"));

    }


    public static void cropsMenu(Scanner scanner) {
        while (true) {
            System.out.println("-----Crops Menu-----");
            System.out.println("1. View Crops");
            System.out.println("2. Add Crop");
            System.out.println("3. Remove Crop");
            System.out.println("4. Back to Main Menu");
            System.out.println("Choose Your Option: ");

            int option = readUserInput(scanner);

            switch (option) {
                case 1:
                    viewCrops(scanner);
                    break;
                case 2:
                    addCrop(scanner, getCrops());
                    break;
                case 3:
                    removeCrop(scanner);
                    break;
                case 4:
                    return; // Exit the method to go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static void addCrop(Scanner scanner, ArrayList<Crop> crops) {
        System.out.println("Enter the name of the crop:");
        String name = scanner.nextLine();

        // Crop type selection menu
        System.out.println("Select a crop type:");
        System.out.println("1. Grass");
        System.out.println("2. Grain");
        System.out.println("3. Vegetable");
        System.out.println("4. Meat");

        String type;
        int choice;
        do {
            System.out.print("Enter the corresponding number: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    type = "Grass";
                    break;
                case 2:
                    type = "Grain";
                    break;
                case 3:
                    type = "Vegetable";
                    break;
                case 4:
                    type = "Meat";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
                    type = null;
            }
        } while (type == null);

        System.out.println("Enter the quantity of the crop:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter a description for the crop:");
        String description = scanner.nextLine();

        int generatedId = IdGenerator.generateId();
        Crop newCrop = new Crop(generatedId, name, type, quantity, description);

        // Add the new crop to the list of crops
        crops.add(newCrop);

        System.out.println("You added a " + newCrop.getName() + " to the farm.");
    }


    public static void viewCrops(Scanner scanner) {
        if (crops.isEmpty()) {
            System.out.println("There are no crops on the farm.");
        } else {
            System.out.println("List of crops:");
            for (Crop crop : crops) {
                System.out.print("ID: " + crop.getId() + " ");
                System.out.print("Name: " + crop.getName() + " ");
                System.out.print("Type: " + crop.getType() + " ");
                System.out.print("Description: " + crop.getDescription() + " ");
                System.out.print("Quantity: " + crop.getQuantity() + " ");
                System.out.println();
            }
            System.out.println();
        }
    }
    public static ArrayList<Crop> getCrops() {
        return crops;
    }

    public static Crop selectCrop(Scanner scanner) {
        System.out.println("Select a crop by entering its ID:");

        viewCrops(scanner);

        int selectedCropId = readUserInput(scanner);

        for (Crop crop : crops) {
            if (crop.getId() == selectedCropId) {
                return crop;
            }
        }

        System.out.println("Invalid crop selection.");
        return null;
    }
    public static void removeCrop(Scanner scanner) {
        System.out.println("Select a crop to remove: ");
        for (int i = 0; i < crops.size(); i++) {
            if (crops.get(i).getQuantity() > 0) {
                System.out.println((i + 1) + ". " + crops.get(i).getName());
            }
        }

        // Get user input for the selected crop
        int selectedCropIndex;
        do {
            System.out.print("Enter the corresponding number: ");
            selectedCropIndex = scanner.nextInt();
            scanner.nextLine();

            if (selectedCropIndex < 1 || selectedCropIndex > crops.size() || crops.get(selectedCropIndex - 1).getQuantity() == 0) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (selectedCropIndex < 1 || selectedCropIndex > crops.size() || crops.get(selectedCropIndex - 1).getQuantity() == 0);

        Crop selectedCrop = crops.get(selectedCropIndex - 1);

        // Remove the selected crop from the list
        crops.remove(selectedCrop);

        System.out.println(selectedCrop.getName() + " removed from the farm.");
    }

    private static int readUserInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    public void saveToCSV(FileWriter writer) throws IOException {
        writer.write("ID,Name,Type,Quantity,Description\n");
        for (Crop crop : crops) {
            writer.write(crop.toCSV());
        }
    }
}




