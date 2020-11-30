package com.example.myapplication.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myapplication.infrastruture.utils.ICatDownloadManager
import com.example.myapplication.repositories.interfaces.ICatRepository
import com.example.myapplication.repositories.interfaces.IFavoritesCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class CatListViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mRepository: ICatRepository,
    private val mICatDownloadManager: ICatDownloadManager,
    private val mFavoritesRepository: IFavoritesCatRepository
) : ViewModel() {
    private val mCatList = MutableLiveData<List<CatViewModel>>()
    private val mIsLoading = MutableLiveData<Boolean>(true)

    val catList: LiveData<List<CatViewModel>>
        get() = mCatList

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoading.postValue( true)
            mCatList.postValue(mRepository.getCats().map { cat ->
                CatViewModel(cat)
            })
            mIsLoading.postValue(false)
        }
    }

    fun onLikeCat(cat: CatViewModel) {
        viewModelScope.launch (Dispatchers.IO) {
            mFavoritesRepository.putCat(cat.cat)
        }
    }

    fun onDownloadCat(cat: CatViewModel) {
        mICatDownloadManager.downloadCat(cat.cat)
    }
}