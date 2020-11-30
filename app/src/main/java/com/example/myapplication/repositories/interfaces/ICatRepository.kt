package com.example.myapplication.repositories.interfaces

import com.example.myapplication.model.CatModel

interface ICatRepository {
    suspend fun getCats(): List<CatModel>
}
