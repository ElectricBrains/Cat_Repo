package com.example.myapplication.infrastruture.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.infrastruture.clients.ServerClient
import com.example.myapplication.infrastruture.clients.interfaces.IServerClient
import com.example.myapplication.infrastruture.database.CatDatabase
import com.example.myapplication.infrastruture.utils.CatDownloadQueue
import com.example.myapplication.infrastruture.utils.CatDownloader
import com.example.myapplication.infrastruture.utils.ICatDownloadManager
import com.example.myapplication.infrastruture.utils.IDownloadQueue
import com.example.myapplication.repositories.CatRepository
import com.example.myapplication.repositories.interfaces.IBreedRepository
import com.example.myapplication.repositories.interfaces.ICatRepository
import com.example.myapplication.repositories.interfaces.IFavoritesCatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideServerClient(): IServerClient {
        return ServerClient()
    }

    @Provides
    @Singleton
    fun provideRepo(serverClient: IServerClient, catDatabase: CatDatabase): CatRepository {
        return CatRepository(serverClient, catDatabase)
    }

    @Provides
    @Singleton
    fun provideICatRepository(catRepository: CatRepository): ICatRepository {
        return catRepository
    }

    @Provides
    @Singleton
    fun provideIBreedRepository(catRepository: CatRepository): IBreedRepository {
        return catRepository
    }

    @Provides
    @Singleton
    fun provideICatDownloader(application: Application, catDownloadQueue: IDownloadQueue): ICatDownloadManager {

        return CatDownloader(application, catDownloadQueue)
    }

    @Provides
    @Singleton
    fun provideIDownloadQueue(): IDownloadQueue {
        return CatDownloadQueue()
    }


    @Provides
    @Singleton
    fun provideIFavoritesCatRepository(catRepository: CatRepository): IFavoritesCatRepository {
        return catRepository
    }

    @Provides
    fun provideRoom(application: Application): CatDatabase {
        return Room.databaseBuilder(
            application, CatDatabase::class.java,
            "CatDataBase"
        ).build()
    }
}