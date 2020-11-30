package com.example.myapplication.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myapplication.repositories.interfaces.ICatRepository
import com.example.myapplication.repositories.interfaces.IFavoritesCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class FavoritesCatListViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mRepository: IFavoritesCatRepository
) : ViewModel() {
    val catList: LiveData<List<CatViewModel>>
        get() = Transformations.map(mRepository.getFavoritesCats()) {
            return@map it.map { cat ->
                CatViewModel(cat)
            }
        }

    fun onClickCat(cat: CatViewModel) {
        viewModelScope.launch (Dispatchers.IO) {
            mRepository.delete(cat.cat)
        }
    }
}