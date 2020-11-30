package com.example.myapplication.views.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.viewmodels.CatViewModel

class CatDiffCallback : DiffUtil.ItemCallback<CatViewModel>() {
    override fun areItemsTheSame(oldItem: CatViewModel, newItem: CatViewModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CatViewModel, newItem: CatViewModel): Boolean {
        return oldItem.imageUrl == newItem.imageUrl && oldItem.name == newItem.name
    }
}