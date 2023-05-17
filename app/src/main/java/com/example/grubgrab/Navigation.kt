package com.example.grubgrab

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.grubgrab.customer.CustomerEvent
import com.example.grubgrab.customer.CustomerScreen
import com.example.grubgrab.customer.CustomerState
import com.example.grubgrab.restaurant.RestaurantEvent
import com.example.grubgrab.restaurant.RestaurantScreen
import com.example.grubgrab.restaurant.RestaurantState

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RestaurantScreen.route) {
        composable(route = Screen.RestaurantScreen.route) {
            RestaurantScreen(state = RestaurantState(), onEvent = RestaurantEvent, navController = )
        }
        composable(
            route = Screen.CustomerScreen.route,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            CustomerScreen(state = CustomerState(), onEvent = { event ->
                when (event) {
                    is CustomerEvent.NavigateRestaurant -> {
                        navController.navigate(Screen.CustomerScreen)
                    }
                    is CustomerEvent.CancelNavigation -> {
                        navController.popBackStack()
                    }
                }
            }, navController = navController)
        }
    }
}