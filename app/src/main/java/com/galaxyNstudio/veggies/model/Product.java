package com.galaxyNstudio.veggies.model;

public class Product {
    private String id;
    private String vegetable_name;
    private double new_price;
    private double old_price;
    private String category;
    private String availability;
    private String image;

    public Product(String id,String productName, double newPrice, double oldPrice, String category, String availability, String image) {
        this.id=id;
        this.vegetable_name = productName;
        this.new_price = newPrice;
        this.old_price = oldPrice;
        this.category = category;
        this.availability = availability;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return vegetable_name;
    }

    public double getNewPrice() {
        return new_price;
    }

    public double getOldPrice() {
        return old_price;
    }

    public String getCategory() {
        return category;
    }

    public String getAvailability() {
        return availability;
    }

    public String getImage() {
        return image;
    }
}
