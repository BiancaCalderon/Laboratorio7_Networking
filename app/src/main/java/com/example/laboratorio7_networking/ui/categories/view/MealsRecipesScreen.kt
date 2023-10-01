package com.example.laboratorio7_networking.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import coil.compose.rememberImagePainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laboratorio7_networking.navigation.NavigationState
import com.example.laboratorio7_networking.networking.response.MealsRecipesResponse

import com.example.laboratorio7_networking.networking.response.RecipeResponse
import com.example.laboratorio7_networking.ui.categories.view.MealsRecipesViewModel



@Composable
fun MealsRecipeScreen(categoryId: String, navController: NavController) {
    val viewModel: MealsRecipesViewModel = viewModel()
    val rememberedMeals: MutableState<List<RecipeResponse>> = remember { mutableStateOf(emptyList()) }

    LaunchedEffect(categoryId) {
        viewModel.getMealsRecipe(categoryId) { response ->
            if (response != null) {
                rememberedMeals.value = response.recipes.orEmpty()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Text(
            text = "Category $categoryId",
            modifier = Modifier.padding(2.dp),
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(rememberedMeals.value) { meal ->
                MealsInCategoryCard(meal, navController)
            }
        }
    }
}

@Composable
fun MealsInCategoryCard(recipe: RecipeResponse, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("${NavigationState.Recipe.route}/${recipe.id}")
            },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = recipe.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
