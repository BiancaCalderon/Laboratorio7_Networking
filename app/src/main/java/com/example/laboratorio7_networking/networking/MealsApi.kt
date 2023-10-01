package com.example.laboratorio7_networking.networking

import com.example.laboratorio7_networking.networking.response.MealsCategoriesResponse
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.example.laboratorio7_networking.networking.response.MealsRecipesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    fun getMeals(): Call<MealsCategoriesResponse>

    @GET("filter.php")
    fun getMealsRecipe(@Query("c") category:String): Call<MealsRecipesResponse>

    @GET("lookup.php")
    fun getMealById(@Query("i") mealId:String): Call<MealsDetailResponse>


}