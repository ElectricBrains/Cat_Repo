package com.example.myapplication.infrastruture.clients.interfaces

import com.example.myapplication.model.server_dto.ServerCat
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface IApiDefinition {
    @GET("images/search")
    fun getCats(@Query("limit") limit: Int): Deferred<List<ServerCat>>
}