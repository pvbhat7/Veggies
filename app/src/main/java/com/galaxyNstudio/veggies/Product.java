package com.galaxyNstudio.veggies;

public class Product {
    private String productName;
    private double newPrice;
    private double oldPrice;
    private String category;
    private String availability;
    private String image;

    public Product(String productName, double newPrice, double oldPrice, String category, String availability, String image) {
        this.productName = productName;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.category = category;
        this.availability = availability;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public double getOldPrice() {
        return oldPrice;
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
