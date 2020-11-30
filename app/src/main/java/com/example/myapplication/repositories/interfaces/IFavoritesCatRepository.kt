package com.example.myapplication.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.myapplication.model.CatModel
import com.example.myapplication.model.local_dto.FavoriteCat

interface IFavoritesCatRepository {
    fun getFavoritesCats(): LiveData<List<CatModel>>
    suspend fun putCat(cat: CatModel)
    suspend fun delete(cat: CatModel)
}