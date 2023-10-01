package com.example.laboratorio7_networking.ui.meals.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.laboratorio7_networking.navigation.NavigationState
import com.example.laboratorio7_networking.networking.response.MealResponse


@Composable
fun MealsCategoriesScreen(navController: NavController) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList()) }

    // Use LaunchedEffect to trigger the API call when the screen is first displayed
    LaunchedEffect(viewModel) {
        viewModel.getMeals { response ->
            val mealsFromTheApi = response?.categories
            rememberedMeals.value = mealsFromTheApi.orEmpty()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(rememberedMeals.value) { meal ->
                // Display the item with a colored background
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(NavigationState.Recipe.route + "/${meal.id}")
                        }
                ) {
                    // Load the image using Coil and rememberImagePainter
                    Image(
                        painter = rememberImagePainter(meal.imageUrl),
                        contentDescription = meal.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )

                    // Display the category name and description
                    Text(
                        text = meal.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = meal.description,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

