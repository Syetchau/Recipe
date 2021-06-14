package com.example.recipeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.models.RecipeResponse
import com.example.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeRepository: RecipeRepository): ViewModel() {

    private val _response = MutableLiveData<RecipeResponse>()
    val recipeResponse: LiveData<RecipeResponse> get() = _response

    init {
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {

        recipeRepository.getRecipe().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("Response Error", "getRecipe: ${response.code()}")
            }
        }
    }
}