package com.example.grubgrab.restaurant

data class RestaurantState(
    val restaurants: List<Restaurant> = emptyList(),
    val restaurantName: String = "",
    val contactNum: String = "",
    val address: String = "",
    val isAddingRestaurant: Boolean = false,
    val sortType: SortType = SortType.NAME_ASC
)
