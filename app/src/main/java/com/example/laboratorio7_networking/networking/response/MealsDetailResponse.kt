package com.example.laboratorio7_networking.networking.response

import com.google.gson.annotations.SerializedName

data class MealsDetailResponse( @SerializedName("meals") val meals: List<DetailResponse>?)


data class DetailResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strInstructions") val instruction: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strArea") val strArea: String,
    @SerializedName("strMealThumb") val imageUrl: String
)