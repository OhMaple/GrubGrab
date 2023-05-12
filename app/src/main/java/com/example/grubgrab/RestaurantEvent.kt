package com.example.grubgrab

sealed interface RestaurantEvent{
    object SaveRestaurant: RestaurantEvent
    data class SetRestaurantName(val restaurantName: String): RestaurantEvent
    data class SetRestaurantContactNum(val contactNum: String): RestaurantEvent
    data class SetRestaurantAddress(val address: String): RestaurantEvent
    object ShowDialog: RestaurantEvent
    object HideDialog: RestaurantEvent
    data class SortRestaurants(val sortType: SortType): RestaurantEvent
    data class DeleteRestaurant(val restaurant: Restaurant): RestaurantEvent
}