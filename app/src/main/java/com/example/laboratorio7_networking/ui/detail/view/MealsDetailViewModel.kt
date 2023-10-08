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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    fun getMealById(mealId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getMealById(mealId)
                _mealDetail.value = response
            } catch (e: Exception) {
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
