package com.example.myapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myapplication.infrastruture.clients.interfaces.IServerClient
import com.example.myapplication.infrastruture.database.CatDatabase
import com.example.myapplication.model.CatModel
import com.example.myapplication.model.local_dto.Breed
import com.example.myapplication.model.local_dto.FavoriteCat
import com.example.myapplication.repositories.interfaces.IBreedRepository
import com.example.myapplication.repositories.interfaces.ICatRepository
import com.example.myapplication.repositories.interfaces.IFavoritesCatRepository

class CatRepository(private val mServerClient: IServerClient, private val mCatDatabase: CatDatabase) : ICatRepository, IFavoritesCatRepository,
    IBreedRepository {
    override suspend fun getCats(): List<CatModel> {
        return mServerClient.getServerCats()
    }

    override fun getFavoritesCats(): LiveData<List<CatModel>> {
        return Transformations.map(mCatDatabase.Dao().favoritesCats()){list ->
            return@map list.map {
                CatModel(it.name, it.imageUrl)
            }
        }
    }

    override suspend fun putCat(cat: CatModel) {
        mCatDatabase.Dao().insert(FavoriteCat().apply {
            name = cat.name
            imageUrl = cat.imageUrl
        })
    }

    override suspend fun delete(cat: CatModel) {
        mCatDatabase.Dao().delete(cat)
    }

    override suspend fun putBreed(breed: Breed) {
        mCatDatabase.Dao().insert(breed)
    }

}