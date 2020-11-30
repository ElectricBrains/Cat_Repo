package com.example.myapplication.infrastruture.clients.interfaces

import com.example.myapplication.model.CatModel

interface IServerClient {
    suspend fun getServerCats(): List<CatModel>
}