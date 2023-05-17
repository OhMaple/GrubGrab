package com.example.grubgrab.customer

import com.example.grubgrab.restaurant.RestaurantEvent

data class CustomerState(
    val customers: List<Customer> = emptyList(),
    val customerFirstName: String = "",
    val customerLastName: String = "",
    val address: String = "",
    val contactNum: String = "",
    val isAddingCustomer: Boolean = false,
    val sortType: SortType = SortType.FNAME_ASC
)
