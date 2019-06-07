package com.galaxyNstudio.veggies.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galaxyNstudio.veggies.Entities.Cart;
import com.galaxyNstudio.veggies.Entities.ProductEntity;

import java.util.List;

@Dao
public interface VegetableDao {

    @Query("SELECT * FROM leafy_vegetable")
    List<ProductEntity> getAllLeafyVegetable();

    @Insert
    void insertAllLeafyVegetable(List<ProductEntity> product);

    @Delete
    void deleteLeafyVegetable(ProductEntity product);

    @Update
    void updateLeafyVegetable(ProductEntity product);

    @Query("delete from leafy_vegetable")
    void deleteAllLeafyVegetable();


}
