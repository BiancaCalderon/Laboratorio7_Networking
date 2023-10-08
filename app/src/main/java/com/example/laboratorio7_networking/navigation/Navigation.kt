package com.example.laboratorio7_networking.navigation


import MealsDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio7_networking.ui.categories.view.MealsRecipeScreen
import com.example.laboratorio7_networking.ui.meals.view.MealsCategoriesScreen


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationState.Meals.route,
        modifier = modifier
    ) {
        composable(route = NavigationState.Meals.route) {
            MealsCategoriesScreen(navController)
        }
        composable(route = "${NavigationState.Recipe.route}/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            MealsRecipeScreen(categoryId, navController)
        }
        composable(route = "${NavigationState.Detail.route}/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealsDetailScreen(mealId, navController)

        }
    }
}








