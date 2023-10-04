package com.example.laboratorio7_networking.ui.detail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@Composable
fun MealsDetailScreen(recipeId: String, navController: NavController) {
    val viewModel: MealsDetailViewModel = viewModel()

    LaunchedEffect(recipeId) {
        viewModel.getMealById(recipeId)
    }

    val mealDetail by viewModel.mealDetail.collectAsState(null)

    val currentRecipeId by rememberUpdatedState(recipeId)


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = { navController.navigate("Category/${mealDetail!!.meals?.first()?.category}") }) {
            Text(text = "Back")
        }
        if (mealDetail != null) {

            Text(
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.h6,
                color = Color.White,
                text = mealDetail!!.meals?.first()?.name ?: "Trabajando....")

            Card( modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "Details")
                Text(text = ("Area: " + mealDetail!!.meals?.first()?.strArea) ?: "N/A")
                Text(text = ("Category: "+ mealDetail!!.meals?.first()?.category) ?: "N/A")

            }



            Card( modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {



                Text(text = "Instructions: ")
                Text(text = mealDetail!!.meals?.first()?.instruction ?: "N/A")

                Image(

                    painter = rememberImagePainter( mealDetail!!.meals?.first()?.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }





        } else {
            Text("--Comida preparandose--")
        }

    }
}