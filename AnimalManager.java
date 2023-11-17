package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {

    private static ArrayList<Animal> animals;
    private static ArrayList<Crop> crops;
    Scanner scanner = new Scanner(System.in);
    private IdGenerator animalIdGenerator;
    private static final IdGenerator idGenerator = new IdGenerator();

    public AnimalManager() {
        this.animals = new ArrayList<>();
        this.crops = new ArrayList<>();
        animals = new ArrayList<>();
        crops = new ArrayList<>();
        animalIdGenerator = new IdGenerator();

        animals.add(new Animal(animalIdGenerator.generateId(), "Horse", 8, "Mammal", "Grass", 5, "Default description"));
        animals.add(new Animal(animalIdGenerator.generateId(), "Cow", 6, "Mammal", "Grass", 10, "We raise cows for dairy production."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Chicken", 2, "Bird", "Grain", 20, "We have chickens that lay eggs."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Sheep", 4, "Mammal", "Grass", 15, "Our sheep provides wool.."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Pig", 3, "Mammal", "Vegetables", 8, "We raise pigs for pork production."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Goat", 4, "Mammal", "Grass", 12, "We have goats that produce milk and cheese."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Rabbit", 1, "Mammal", "Vegetables", 1, "We raise rabbits as pets."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Duck", 2, "Bird", "Grain", 12, "Our ducks are kept for their eggs."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Turkey", 2, "Bird", "Grain", 5, "We have turkeys for meat production."));
        animals.add(new Animal(animalIdGenerator.generateId(), "Donkey", 7, "Mammal", "Grass", 4, "We use donkeys for farm work."));

    }

    public static void AnimalMenu(Scanner scanner) {
        while (true) {
            System.out.println("-----Animals Menu-----");
            System.out.println("1. View Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Remove Animal");
            System.out.println("4. Feed Animal");
            System.out.println("5. Back to Main Menu");
            System.out.println("Choose Your Option: ");

            int option = readUserInput(scanner);

            switch (option) {
                case 1:
                    viewAnimals(scanner);
                    break;
                case 2:
                    addAnimal(scanner, animals);
                    break;
                case 3:
                    removeAnimal(scanner);
                    break;
                case 4:
                    Animal selectedAnimal = selectAnimal(scanner, (ArrayList<Animal>) animals);
                    if (selectedAnimal != null) {
                        feedAnimal(selectedAnimal, scanner);
                    }
                    break;
                case 5:
                    return; // Exit the method to go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    public static void addAnimal(Scanner scanner, ArrayList<Animal> animals) {
        System.out.println("Enter the name of the animal:");
        String name = scanner.nextLine();

        System.out.println("Enter the age of the animal:");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the species of the animal:");
        String species = scanner.nextLine();

        // Crop type selection menu
        System.out.println("Select a crop type for the animal:");
        System.out.println("1. Grass");
        System.out.println("2. Grain");
        System.out.println("3. Vegetable");
        System.out.println("4. Meat");

        String cropType;
        int choice;
        do {
            System.out.print("Enter the corresponding number: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cropType = "Grass";
                    break;
                case 2:
                    cropType = "Grain";
                    break;
                case 3:
                    cropType = "Vegetable";
                    break;
                case 4:
                    cropType = "Meat";
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
                    cropType = null;
            }
        } while (cropType == null);

        System.out.println("Enter the quantity of the animal:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter a description for the animal:");
        String description = scanner.nextLine();

        int generatedId = IdGenerator.generateId();
        Animal newAnimal = new Animal(generatedId, name, age, species, cropType, quantity, description);

        // Add the new animal to the list
        animals.add(newAnimal);

        System.out.println("You added a " + newAnimal.getName() + " to the farm.");
    }


    public static void viewAnimals(Scanner scanner) {
        if (animals.isEmpty()) {
            System.out.println("There are no animals on the farm.");
        } else {
            System.out.println("List of animals:");
            for (Animal animal : animals) {
                System.out.print("ID: " + animal.getId() + " ");
                System.out.print("Name: " + animal.getName() + " ");
                System.out.print("Age: " + animal.getAge() + " ");
                System.out.print("Species: " + animal.getSpecies() + " ");
                System.out.print("Crop Type: " + animal.getCropType() + " ");
                System.out.print("Description: " + animal.getDescription() + " ");
                System.out.print("Quantity: " + animal.getQuantity() + " ");
                System.out.println();

            }
            System.out.println();
        }
    }



    public static Animal selectAnimal(Scanner scanner, ArrayList<Animal> animalsList) {
            System.out.println("Select an animal by entering its ID:");

            for (Animal animal : animalsList) {
                System.out.println("ID: " + animal.getId() + ", Name: " + animal.getName());
            }

            int selectedAnimalId = readUserInput(scanner);

            for (Animal animal : animalsList) {
                if (animal.getId() == selectedAnimalId) {
                    return animal;
                }
            }

            System.out.println("Invalid animal selection.");
            return null;
        }



    public static void removeAnimal(Scanner scanner) {
        System.out.println("Select an animal to remove: ");
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName());
        }

        // Get user input for the selected animal
        int selectedAnimalIndex;
        do {
            System.out.print("Enter the corresponding number: ");
            selectedAnimalIndex = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (selectedAnimalIndex < 1 || selectedAnimalIndex > animals.size()) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        } while (selectedAnimalIndex < 1 || selectedAnimalIndex > animals.size());

        Animal selectedAnimal = animals.get(selectedAnimalIndex - 1);

        // Remove the selected animal from the list
        animals.remove(selectedAnimal);

        System.out.println(selectedAnimal.getName() + " removed from the farm.");
    }

    public static void feedAnimal(Animal selectedAnimal, Scanner scanner) {
        while (true) {
            Crop selectedCrop = CropManager.selectCrop(scanner);

            if (selectedCrop != null) {
                if (selectedAnimal.acceptableCropType(selectedCrop.getType())) {
                    if (selectedCrop.getQuantity() > 0) {
                        selectedCrop.decreaseQuantity();
                        System.out.println(selectedAnimal.getName() + " (ID: " + selectedAnimal.getId() +
                                ") has been fed with " + selectedCrop.getType() + ": " + selectedCrop.getName());
                        return; // Exit the loop and go back to the crop selection menu
                    } else {
                        System.out.println("Not enough " + selectedCrop.getName() + " available.");
                    }
                } else {
                    System.out.println(selectedAnimal.getName() + " (ID: " + selectedAnimal.getId() +
                            ") cannot eat " + selectedCrop.getType());
                }
            } else {
                // Invalid crop selection, ask the user to try again
            }
        }
    }

    public static ArrayList<Animal> getAnimals() {
        return animals;
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
        writer.write("ID,Name,Age,Species,CropType,Quantity,Description\n");
        for (Animal animal : animals) {
            writer.write(animal.toCSV());
        }
    }
}


