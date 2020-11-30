package com.example.myapplication.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myapplication.model.local_dto.Breed
import com.example.myapplication.repositories.interfaces.IBreedRepository
import com.example.myapplication.repositories.interfaces.ICatRepository
import com.example.myapplication.repositories.interfaces.IFavoritesCatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SaveCatBreedViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mRepository: IBreedRepository
) : ViewModel() {
    private val mOnBreedSaved = MediatorLiveData<Boolean>()
    val onBreedSaved: LiveData<Boolean>
        get() = mOnBreedSaved

    val breed =  MutableLiveData<String> ("")

    fun onSave(cat: CatViewModel) {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                mRepository.putBreed(Breed().apply {
                    name = breed.value?: cat.name
                    imageUrl = cat.imageUrl
                })
                mOnBreedSaved.postValue(true)
            } catch (e: Exception) {
                mOnBreedSaved.postValue(false)
            }
        }
    }
}