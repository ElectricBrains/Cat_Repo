package com.example.myapplication.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.myapplication.model.CatModel
import com.example.myapplication.model.local_dto.Breed
import com.example.myapplication.model.local_dto.FavoriteCat

interface IBreedRepository {
    suspend fun putBreed(breed: Breed)
}