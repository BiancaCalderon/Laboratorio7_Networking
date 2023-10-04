package com.example.laboratorio7_networking.ui.detail.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.example.laboratorio7_networking.ui.detail.repository.MealsDetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MealsDetailViewModel(
    private val repository: MealsDetailRepository = MealsDetailRepository()
) : ViewModel() {

    private val _mealDetail = MutableStateFlow<MealsDetailResponse?>(null)
    val mealDetail: StateFlow<MealsDetailResponse?> get() = _mealDetail

    fun getMealById(recipeId: String) {
        viewModelScope.launch {
            val response = repository.getMealById(recipeId)
            _mealDetail.value = response
        }
    }
}
