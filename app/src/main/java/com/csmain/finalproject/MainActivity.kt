package com.csmain.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.csmain.feature.home.navigation.HomeScreen
import com.csmain.feature.home.presentation.HomeScreen
import com.csmain.feature_cart.navigation.CartScreen
import com.csmain.feature_details.navigation.DetailsScreen
import com.csmain.feature_details.ui.DetailsScreen
import dagger.hilt.android.AndroidEntryPoint
import com.example.feature_profile.navigation.ProfileScreen
import com.example.feature_profile.ui.ProfileScreen
import com.csmain.feature_cart.ui.CartScreen

@AndroidEntryPoint
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
                        onProductClick = { id -> navController.navigate(DetailsScreen(id)) },
                        onCartClick = { navController.navigate(CartScreen) },
                        onProfileClick = { navController.navigate(ProfileScreen) },
                    )
                }
                composable<DetailsScreen> { entry ->
                    val route = entry.toRoute<DetailsScreen>()
                    DetailsScreen(
                        onBackClick = { navController.popBackStack() },
                    )
                }
                composable<CartScreen> {
                    CartScreen(onBackClick = { navController.popBackStack()})
                }
                composable<ProfileScreen> {
                    ProfileScreen()
                }
            }
        }
    }
}