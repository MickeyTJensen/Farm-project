package org.example;

import java.io.*;
import java.util.Scanner;

public class Farm {
    private String name;
    private AnimalManager animalManager;
    private CropManager cropManager;

    public Farm(String name) {
        this.name = name;
        this.animalManager = new AnimalManager();
        this.cropManager = new CropManager();
    }

    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            MainMenu(scanner);
        }

        scanner.close();
    }

    private void MainMenu(Scanner scanner) throws IOException {
        System.out.println("-----Jensens FarmVille-----");
        System.out.println("1. Overlook the farm");
        System.out.println("2. Animals Menu");
        System.out.println("3. Crops Menu");
        System.out.println("4. Save");
        System.out.println("5. ----Exit----");
        System.out.println("Choose Your Option: ");

        int option = readUserInput(scanner);

        switch (option) {
            case 1:
                overlookFarm(scanner, animalManager, cropManager);
                break;
            case 2:
                animalManager.AnimalMenu(scanner);
                break;
            case 3:
                cropManager.cropsMenu(scanner);
                break;
            case 4:
                break;
            case 5:
                System.out.println("Exiting... Thanks for visiting Jensens FarmVille!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void overlookFarm(Scanner scanner, AnimalManager animalManager, CropManager cropManager) {
        System.out.println("Jensens FarmVille");
        System.out.println("Animals: ");
        animalManager.viewAnimals(scanner);
        System.out.println("Crops: ");
        cropManager.viewCrops(scanner);
    }


    public AnimalManager getAnimalManager() {

        return this.animalManager;
    }
    public CropManager getCropManager() {

        return this.cropManager;
    }

    private static int readUserInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

}