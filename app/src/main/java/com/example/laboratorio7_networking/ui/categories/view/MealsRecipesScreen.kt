package com.example.laboratorio7_networking.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.laboratorio7_networking.navigation.NavigationState
import com.example.laboratorio7_networking.networking.response.RecipeResponse



@Composable
fun MealsRecipeScreen(categoryId: String?, navController: NavController) {
    val viewModel: MealsRecipesViewModel = viewModel()
    val rememberedMeals: MutableState<List<RecipeResponse>?> = remember { mutableStateOf(emptyList<RecipeResponse>()) }

    LaunchedEffect(categoryId) {
        if (categoryId != null) {
            viewModel.getMealsRecipe(categoryId) { response ->
                if (response != null) {
                    rememberedMeals.value = response.recipes
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
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
        rememberedMeals.value?.let { meals ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(meals) { meal ->
                    CategoryCard(meal, navController)
                }
            }
        } ?: run {

            Text(
                text = "No meals available for this category ;(",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }
}



@Composable
fun CategoryCard(recipe: RecipeResponse, navController: NavController) {
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
            val painter = rememberImagePainter(
                data = recipe.imageUrl,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp))

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recipe.name, fontWeight = FontWeight.Bold)
        }
    }
}

