package org.example;

import java.io.*;

public class FileHandler {
    public static void Save(Farm farm, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Save farm data to CSV
            writer.write("----FarmName," + farm.getName() + "----" + "\n");

            // Save animals with header
            writer.write("----Animals---\n");
            farm.getAnimalManager().saveToCSV(writer);

            // Save crops with header
            writer.write("\n----Crops---\n");
            farm.getCropManager().saveToCSV(writer);

            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace(); // or log the exception
        }
    }

    public static void Load(Farm farm, String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip the header line
            line = br.readLine(); // Read the first line of data
            while (line != null) {
                try {
                    // Split the line into fields
                    String[] fields = line.split(",");
                    if (fields.length == 7) {
                        // Check if the first field is a valid integer (skipping the header)
                        if (!fields[0].equals("ID")) {
                            // Animal data
                            int id = Integer.parseInt(fields[0]);
                            String name = fields[1];
                            int age = Integer.parseInt(fields[2]);
                            String species = fields[3];
                            String cropType = fields[4];
                            int quantity = Integer.parseInt(fields[5]);
                            String description = fields[6];

                            farm.getAnimalManager().getAnimals().add(new Animal(id, name, age, species, cropType, quantity, description));
                        }
                    } else if (fields.length == 5) {
                        // Crop data
                        int id = Integer.parseInt(fields[0]);
                        String name = fields[1];
                        String type = fields[2];
                        int quantity = Integer.parseInt(fields[3]);
                        String description = fields[4];

                        farm.getCropManager().getCrops().add(new Crop(id, name, type, quantity, description));
                    }
                } catch (Exception e) {
                    // Handle parsing-related exceptions if needed
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace(System.out);
                }

                line = br.readLine();
            }

            System.out.println("Data loaded from " + fileName);

        } catch (IOException e) {
            // Handle file-related exceptions if needed
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace(System.out);
        }
    }
}
