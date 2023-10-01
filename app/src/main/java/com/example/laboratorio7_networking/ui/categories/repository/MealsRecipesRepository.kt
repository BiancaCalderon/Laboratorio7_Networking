package com.example.laboratorio7_networking.ui.categories.repository

import com.example.laboratorio7_networking.networking.MealsWebService
import com.example.laboratorio7_networking.networking.response.MealsRecipesResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRecipesRepository(private val webService: MealsWebService = MealsWebService()) {
fun getMealsRecipe(categoryId: String, successCallback: (response: MealsRecipesResponse?) -> Unit) {
    webService.getMealsRecipe(categoryId).enqueue(object : Callback<MealsRecipesResponse> {
        override fun onResponse(
            call: Call<MealsRecipesResponse>,
            response: Response<MealsRecipesResponse>
        ) {
            if (response.isSuccessful)
                successCallback(response.body())
        }

        override fun onFailure(call: Call<MealsRecipesResponse>, t: Throwable) {
            // TODO treat failure
        }
    })
    }
}