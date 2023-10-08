package com.example.laboratorio7_networking.ui.detail.repository

import com.example.laboratorio7_networking.networking.MealsWebService
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.google.gson.JsonParseException

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class MealsDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealById(mealId: String): MealsDetailResponse? {
        println("Attempting to fetch meal detail with mealId: $mealId")
        println("URL: https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealId")

        return withContext(Dispatchers.IO) {
            try {
                val response = webService.getMealById(mealId)
                println("Response: $response")

                if (response != null) {
                    println("Successful response with data")
                    response
                } else {
                    println("Empty or invalid response")
                    null
                }
            } catch (e: IOException) {
                println("Network error: ${e.localizedMessage}")
                null
            } catch (e: JsonParseException) {
                println("JSON parsing error: ${e.localizedMessage}")
                null
            } catch (e: Exception) {
                println("Unknown error: ${e.localizedMessage}")
                null
            }
        }
    }
}

