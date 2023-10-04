package com.example.laboratorio7_networking.ui.detail.repository

import com.example.laboratorio7_networking.networking.MealsWebService
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MealsDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealById(recipeId: String): MealsDetailResponse? {
        println("Attempting to fetch meal detail with recipeId: $recipeId")
        println("URL: https://www.themealdb.com/api/json/v1/1/lookup.php?i=$recipeId")
        return withContext(Dispatchers.IO) {
            try {
                val response = webService.getMealById(recipeId)
                println("Response: $response")
                response
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}")
                null
            }
        }
    }
}


