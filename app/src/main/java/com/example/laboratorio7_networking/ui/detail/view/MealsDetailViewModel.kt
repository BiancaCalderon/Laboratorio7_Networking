package com.example.laboratorio7_networking.ui.detail.view

import androidx.lifecycle.ViewModel
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.example.laboratorio7_networking.ui.detail.repository.MealsDetailRepository

class MealsDetailViewModel(private val repository: MealsDetailRepository = MealsDetailRepository()) : ViewModel() {
    fun getMealById(recipeId: String, successCallback: (response: MealsDetailResponse?) -> Unit) {
        repository.getMealById(recipeId) { response ->
            successCallback(response)
        }
    }
}