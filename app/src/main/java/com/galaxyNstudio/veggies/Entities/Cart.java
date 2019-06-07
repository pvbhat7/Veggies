package com.galaxyNstudio.veggies.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Cart implements Serializable {

    public Cart(String productId, String vegetableName, Double oldPrice, Double newPrice, String availability, String category,String image) {
        this.productId = productId;
        this.vegetableName = vegetableName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.availability = availability;
        this.category = category;
        this.image=image;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "productId")
    private String productId;

    @ColumnInfo(name = "vegetableName")
    private String vegetableName ;

    @ColumnInfo(name = "oldPrice")
    private Double oldPrice;

    @ColumnInfo(name = "newPrice")
    private Double newPrice;

    @ColumnInfo(name = "availability")
    private String availability;

    @ColumnInfo(name = "category")
    private String category;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @ColumnInfo(name = "image")
    private String image;

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public String getCategory() {
        return category;
    }
}
