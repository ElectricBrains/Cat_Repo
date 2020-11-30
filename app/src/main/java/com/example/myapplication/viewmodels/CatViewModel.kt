package com.example.myapplication.viewmodels

import com.example.myapplication.model.CatModel
import java.io.Serializable

data class CatViewModel (val cat: CatModel, val name: String = cat.name, val imageUrl: String = cat.imageUrl): Serializable