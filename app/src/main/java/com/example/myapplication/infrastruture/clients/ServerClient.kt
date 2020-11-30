package com.example.myapplication.infrastruture.clients

import com.example.myapplication.infrastruture.clients.interfaces.IApiDefinition
import com.example.myapplication.infrastruture.clients.interfaces.IServerClient
import com.example.myapplication.model.CatModel
import com.example.myapplication.model.server_dto.ServerCat
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerClient: IServerClient {
    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"
        private const val LIMIT = 100
    }

    val mService: IApiDefinition

    init {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        mService = retrofit.create(IApiDefinition::class.java)

    }

    override suspend fun getServerCats(): List<CatModel> {
        val serverResult: List<ServerCat> = mService.getCats(LIMIT).await()
        return serverResult.map { serverCat ->
            CatModel( serverCat.id, serverCat.url)
        }
    }
}