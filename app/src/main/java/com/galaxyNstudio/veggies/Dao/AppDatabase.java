package com.galaxyNstudio.veggies.Dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.galaxyNstudio.veggies.Entities.Cart;
import com.galaxyNstudio.veggies.Entities.ProductEntity;

@Database(entities = {Cart.class, ProductEntity.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
    public abstract VegetableDao vegetableDao();
}
