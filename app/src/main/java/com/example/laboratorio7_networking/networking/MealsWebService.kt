package com.example.laboratorio7_networking.networking

import com.example.laboratorio7_networking.networking.response.MealsCategoriesResponse
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.example.laboratorio7_networking.networking.response.MealsRecipesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MealsWebService {

    private lateinit var api: MealsApi

    init {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }
    fun getMealsRecipe(categoryId: String): Call<MealsRecipesResponse> {
        return api.getMealsRecipe("$categoryId")
    }
    suspend fun getMealById(mealId: String): MealsDetailResponse? {
        return api.getMealById(mealId)
    }

}