package com.galaxyNstudio.veggies.responseWrapper;

import com.galaxyNstudio.veggies.model.Product;

import java.util.ArrayList;
import java.util.List;

public class VegetableWrapper {

    Boolean error;
    List<Product> vegetables;

    public VegetableWrapper(Boolean error, List<Product> vegetables) {
        this.error = error;
        this.vegetables = vegetables;
    }

    public Boolean getError() {
        return error;
    }

    public List<Product> getVegetableList() {
        return vegetables;
    }
}
