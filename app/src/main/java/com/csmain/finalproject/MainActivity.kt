package com.csmain.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.csmain.common.ui.theme.FinalProjectTheme
import com.csmain.feature.home.navigation.HomeScreen
import com.csmain.feature.home.presentation.HomeScreen
import com.csmain.feature_details.navigation.DetailsScreen
import com.csmain.feature_details.ui.DetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = HomeScreen,
            ) {
                composable<HomeScreen> {
                    HomeScreen(
                        onDetailsClick = { id -> navController.navigate(DetailsScreen(id)) },
                    )
                }
                composable<DetailsScreen> { entry ->
                    val route = entry.toRoute<DetailsScreen>()
                    DetailsScreen(
                        id = route.id,
                        onBackClick = { navController.popBackStack() },
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinalProjectTheme {
        Greeting("Android")
    }
}
