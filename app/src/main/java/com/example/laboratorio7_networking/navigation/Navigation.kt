package com.example.laboratorio7_networking.navigation

import MealsRecipesScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio7_networking.ui.detail.view.DetailView
import com.example.laboratorio7_networking.ui.meals.view.MealsCategoriesScreen

import com.example.laboratorio7_networking.ui.profile.view.ProfileView
import com.example.laboratorio7_networking.ui.venues.view.VenuesView


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Meals.route,
        modifier = modifier) {

        composable(route = NavigationState.Meals.route) {
            MealsCategoriesScreen()
        }

        composable(route = NavigationState.Recipe.route) {
            MealsRecipesScreen("1")
        }

        composable(route = NavigationState.Recipe.route + "/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            MealsRecipesScreen(categoryId)
        }


    }
}