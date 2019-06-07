package com.galaxyNstudio.veggies.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.galaxyNstudio.veggies.Entities.Cart;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    List<Cart> getAll();

    @Insert
    void insert(Cart product);

    @Delete
    void delete(Cart product);

    @Update
    void update(Cart product);

}