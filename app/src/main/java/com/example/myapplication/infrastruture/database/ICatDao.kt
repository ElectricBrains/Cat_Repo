package com.example.myapplication.infrastruture.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.CatModel
import com.example.myapplication.model.local_dto.Breed
import com.example.myapplication.model.local_dto.FavoriteCat

@Dao
abstract class ICatDao {
    @Query("SELECT * FROM FavoriteCat")
    abstract fun favoritesCats(): LiveData<List<FavoriteCat>>

    @Query("SELECT * FROM FavoriteCat")
    abstract fun favorites(): List<FavoriteCat>

    @Delete
    abstract fun delete(cat: FavoriteCat)

    fun delete(cat: CatModel) {
        favorites().firstOrNull{
            it.name == cat.name && it.imageUrl == cat.imageUrl
        }?.let {
            delete(it)
        }
    }

    @Insert
    abstract fun insert(cat: FavoriteCat)

    @Insert
    abstract fun insert(cat: Breed)

    @Update
    abstract fun update(cat: FavoriteCat)

}