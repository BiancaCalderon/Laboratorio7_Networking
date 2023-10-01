package com.example.laboratorio7_networking.navigation

sealed class NavigationState(val route: String) {
    object Meals: NavigationState("Meals")
    object Recipe: NavigationState("Category/{categoryId}")
    object Detail: NavigationState("MealDetail/{recipeId}")
}