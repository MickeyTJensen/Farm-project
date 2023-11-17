package org.example;

import java.util.concurrent.atomic.AtomicInteger;
public class Entity {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    public String toCSV(){
        return "";
    }


    private int id;
    private String name;
    private int quantity;
    private String description;

    public Entity(int id, String name, int quantity, String description) {
        this.id = idCounter.getAndIncrement();
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            System.out.println("Error: Quantity cannot be negative.");
        }
    }

}



