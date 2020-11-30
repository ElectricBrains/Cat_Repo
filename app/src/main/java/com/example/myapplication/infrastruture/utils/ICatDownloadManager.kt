package com.example.myapplication.infrastruture.utils

import android.content.Context
import androidx.annotation.MainThread
import com.example.myapplication.model.CatModel

interface ICatDownloadManager {
    @MainThread
    fun downloadCat(cat: CatModel)
}