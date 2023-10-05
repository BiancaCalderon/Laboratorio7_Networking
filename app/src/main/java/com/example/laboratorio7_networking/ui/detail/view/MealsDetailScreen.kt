
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.laboratorio7_networking.ui.detail.view.MealsDetailViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MealsDetailScreen(recipeId: String, navController: NavController) {

    val viewModel: MealsDetailViewModel = viewModel()

    viewModel.getMealById(recipeId)

    val mealDetail = viewModel.mealDetail.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        if (mealDetail != null && mealDetail.meals!!.isNotEmpty()) {

            val meal = mealDetail.meals?.get(0)

            Text(
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.h6,
                color = Color.White,
                text = meal?.name ?: "N/A"
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Area: ${meal?.strArea ?: "N/A"}")
                    Text("Category: ${meal?.category ?: "N/A"}")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Instructions:")
                    Text(text = meal?.instruction ?: "N/A")
                    
                    Image(
                        painter = rememberImagePainter(meal?.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        } else {

            Text("LOADING...", color = Color.White)
        }
    }
}