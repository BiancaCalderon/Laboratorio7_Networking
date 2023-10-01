package com.example.laboratorio7_networking.ui.detail.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse


@Composable
fun MealsDetailsScreen(recipeId: String) {
    val viewModel: MealsDetailViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealsDetailResponse>> = remember { mutableStateOf(emptyList<MealsDetailResponse>()) }

}
