package com.example.grubgrab

sealed class Screen(val route: String) {
    // object MainScreen : Screen("main_screen")
    object RestaurantScreen : Screen("restaurant_screen")
    object CustomerScreen : Screen("customer_screen")
}
