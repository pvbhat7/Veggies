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

    @Query("SELECT id FROM cart where productId = :id")
    Integer checkProductExists(String id);

    @Query("update cart set qty = :qty where productId = :productId")
    void updateProductQty(int qty,String productId);

    @Query("SELECT qty FROM cart where productId = :id")
    int getproductQty(String id);

    @Query("DELETE FROM cart where productId = :id")
    void removeProductFromCart(String id);
}