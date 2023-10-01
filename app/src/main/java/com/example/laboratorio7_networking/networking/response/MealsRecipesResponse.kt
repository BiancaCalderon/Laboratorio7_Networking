package com.example.laboratorio7_networking.networking.response

import com.google.gson.annotations.SerializedName


data class MealsRecipesResponse(@SerializedName("meals") val recipes: List<RecipeResponse>)

data class RecipeResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val imageUrl: String
)