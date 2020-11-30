package com.example.myapplication.model.local_dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteCat {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
    var imageUrl: String = ""
}