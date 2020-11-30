package com.example.myapplication.infrastruture.utils

import com.example.myapplication.model.CatModel

interface IDownloadQueue {
    fun putToEnd(cat: CatModel)
    fun hasNext(): Boolean
    fun getNext(): CatModel
    fun count(): Int
}