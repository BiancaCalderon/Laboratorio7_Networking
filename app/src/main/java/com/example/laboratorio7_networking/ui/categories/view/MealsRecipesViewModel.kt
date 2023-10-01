package com.example.laboratorio7_networking.ui.categories.view

import androidx.lifecycle.ViewModel
import com.example.laboratorio7_networking.networking.response.MealsRecipesResponse
import com.example.laboratorio7_networking.ui.categories.repository.MealsRecipesRepository

class MealsRecipesViewModel(private val repository: MealsRecipesRepository = MealsRecipesRepository()) : ViewModel() {
    fun getMealsRecipe(categoryId: String, successCallback: (response: MealsRecipesResponse?) -> Unit) {
        repository.getMealsRecipe(categoryId) { response ->
            successCallback(response)
        }
    }
}