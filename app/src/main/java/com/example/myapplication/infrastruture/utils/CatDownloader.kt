package com.example.myapplication.infrastruture.utils

import android.content.Context
import android.content.Intent
import androidx.annotation.MainThread
import com.example.myapplication.model.CatModel
import com.example.myapplication.platform.CatDownloaderService

class CatDownloader(private val mContext: Context, private val mDownloadQueue: IDownloadQueue): ICatDownloadManager {

    @MainThread
    override fun downloadCat(cat: CatModel) {
        mDownloadQueue.putToEnd(cat)
        mContext.startService(Intent(mContext, CatDownloaderService::class.java))
    }



}