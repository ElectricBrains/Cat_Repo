package com.example.myapplication.infrastruture.utils

import com.example.myapplication.model.CatModel

class CatDownloadQueue: IDownloadQueue {
    private val mMutableList = mutableListOf<CatModel>()

    @Synchronized
    override fun putToEnd(cat: CatModel) {
        mMutableList.add(cat)
    }

    @Synchronized
    override fun hasNext(): Boolean = mMutableList.isNotEmpty()

    @Synchronized
    override fun getNext(): CatModel{
        val result = mMutableList.first()
        mMutableList.remove(result)
        return result
    }

    @Synchronized
    override fun count(): Int = mMutableList.size
}