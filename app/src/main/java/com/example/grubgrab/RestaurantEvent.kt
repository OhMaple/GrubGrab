package com.example.grubgrab

// Sealed interface 'RestaurantEvent'
sealed interface RestaurantEvent{
    // Saving restaurant event
    object SaveRestaurant: RestaurantEvent


    // Setting the restaurant's name event
    data class SetRestaurantName(val restaurantName: String): RestaurantEvent


    // Setting the restaurant's contact number event
    data class SetRestaurantContactNum(val contactNum: String): RestaurantEvent


    // Setting the restaurant's address event
    data class SetRestaurantAddress(val address: String): RestaurantEvent


    // Showing the dialog window event
    object ShowDialog: RestaurantEvent


    // Hiding the dialog window event
    object HideDialog: RestaurantEvent


    // Sort the restaurants event, triggered when user interacts with sorting components
    data class SortRestaurants(val sortType: SortType): RestaurantEvent


    // Delete the restaurants event, triggered when the user wants to delete a Restaurant
    data class DeleteRestaurant(val restaurant: Restaurant): RestaurantEvent
}