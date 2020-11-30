package com.example.myapplication.infrastruture.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.local_dto.Breed
import com.example.myapplication.model.local_dto.FavoriteCat

@Database(entities = [FavoriteCat::class, Breed::class], version = 2 )
abstract class CatDatabase : RoomDatabase() {
    abstract fun Dao(): ICatDao

}