
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.laboratorio7_networking.networking.response.MealsDetailResponse
import com.example.laboratorio7_networking.ui.detail.view.MealsDetailViewModel


@Composable
fun MealsDetailScreen(mealId: String, navController: NavController) {
    val viewModel: MealsDetailViewModel = viewModel()

    LaunchedEffect(mealId) {
        viewModel.getMealById(mealId)
    }

    val mealDetail by viewModel.mealDetail.collectAsState(null)

    Column(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Detail Recipe",
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        )

        RenderDishInfo(mealDetail)
    }
}

@Composable
fun RenderDishInfo(dishDetail: MealsDetailResponse?) {
    LazyColumn(content = {
        item {
            when (dishDetail) {
                null -> ShowLoadingState()
                else -> ShowDishData(dishDetail)
            }
        }
    })
}

@Composable
fun ShowDishData(dishData: MealsDetailResponse) {
    val dish = dishData.meals?.first()
    Text(
        text = dish?.name ?: "Esperando nombre del platillo...",
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    )
    Text(
        text = dish?.instruction ?: "Instrucciones no disponibles",
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.White
        )
    )
}

@Composable
fun ShowLoadingState() {
    Text(
        "Recopilando información del platillo...",
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.White
        )
    )
}