package com.galaxyNstudio.veggies.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "leafy_vegetable")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "productId")
    private String productId;
    @ColumnInfo(name = "vegetable_name")
    private String vegetable_name;
    @ColumnInfo(name = "new_price")
    private double new_price;
    @ColumnInfo(name = "old_price")
    private double old_price;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "availability")
    private String availability;
    @ColumnInfo(name = "image")
    private String image;

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setVegetable_name(String vegetable_name) {
        this.vegetable_name = vegetable_name;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getVegetable_name() {
        return vegetable_name;
    }

    public double getNew_price() {
        return new_price;
    }

    public double getOld_price() {
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

    public ProductEntity(String productId, String vegetable_name, double new_price, double old_price, String category, String availability, String image) {
        this.productId = productId;
        this.vegetable_name = vegetable_name;
        this.new_price = new_price;
        this.old_price = old_price;
        this.category = category;
        this.availability = availability;
        this.image = image;
    }
}
