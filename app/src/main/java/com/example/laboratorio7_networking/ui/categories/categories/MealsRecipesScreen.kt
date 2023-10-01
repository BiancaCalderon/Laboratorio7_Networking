import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.laboratorio7_networking.networking.response.RecipeResponse
import com.example.laboratorio7_networking.ui.categories.categories.MealsRecipesViewModel

@Composable
fun MealsRecipesScreen(categoryId: String) {
    val viewModel: MealsRecipesViewModel = viewModel()
    val rememberedRecipes: MutableState<List<RecipeResponse>> = remember { mutableStateOf(emptyList()) }

    // Use LaunchedEffect to trigger the API call when the screen is first displayed
    LaunchedEffect(categoryId) {
        viewModel.getMealsRecipe(categoryId) { response ->
            val recipesFromTheApi = response?.recipes
            rememberedRecipes.value = recipesFromTheApi.orEmpty()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(rememberedRecipes.value) { recipe ->
                // Generate a random color for each item
                val randomColor = Color(
                    red = (0..255).random() / 255f,
                    green = (0..255).random() / 255f,
                    blue = (0..255).random() / 255f,
                    alpha = 1f
                )

                // Display the recipe with a colored background
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(randomColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = recipe.name,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
