package com.example.laboratorio7_networking.ui.detail.repository

import com.example.laboratorio7_networking.networking.MealsWebService
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MealsDetailRepository(private val webService: MealsWebService = MealsWebService()) {
    fun getMealById(recipeId: String, successCallback: (response: MealsDetailResponse?) -> Unit) {
        webService.getMealById(recipeId).enqueue(object : Callback<MealsDetailResponse> {
            override fun onResponse(
                call: Call<MealsDetailResponse>,
                response: Response<MealsDetailResponse>
            ) {
                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<MealsDetailResponse>, t: Throwable) {
                // TODO treat failure
            }
        })
    }
}

