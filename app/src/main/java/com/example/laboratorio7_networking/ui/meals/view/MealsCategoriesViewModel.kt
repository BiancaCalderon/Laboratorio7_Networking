package com.example.laboratorio7_networking.ui.meals.view

import androidx.lifecycle.ViewModel
import com.example.laboratorio7_networking.networking.response.MealsCategoriesResponse
import com.example.laboratorio7_networking.ui.meals.repository.MealsRepository


class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}