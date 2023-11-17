package org.example;

import java.util.List;

public class Animal extends Entity {

    private int age;
    private String species;
    private String cropType;
    private String acceptableCropType;
    private List<String> acceptableCropTypes;

    public Animal(int id, String name, int age, String species, String cropType, int quantity, String description) {
        super(id, name, quantity, description);
        this.age = age;
        this.species = species;
        this.cropType = cropType;
        this.acceptableCropType = cropType;

    }
    public boolean acceptableCropType(String cropType) {
        // Check if the passed crop type is in the list of acceptable crop types
        return this.acceptableCropType.equals(cropType);
    }
    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public String getCropType() {
        return cropType;
    }

    @Override
    public String toCSV() {
        // Convert Animal fields to a CSV-formatted string
        String regularData = String.format("%s,%s,%d,%s,%s,%d,%s",
                getId(), getName(), getAge(), getSpecies(), getCropType(), getQuantity(), getDescription());

        // Combine regular data with acceptableCropTypes
        return regularData + "@" + (acceptableCropTypes != null ? String.join(",", acceptableCropTypes) : "") + "\n";
    }




}