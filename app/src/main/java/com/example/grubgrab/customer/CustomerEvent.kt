package com.example.grubgrab.customer

sealed interface CustomerEvent{
    object SaveCustomer: CustomerEvent

    object NavigateRestaurant: CustomerEvent

    object CancelNavigation: CustomerEvent

    data class SetCustomerFirstName(val firstName: String): CustomerEvent

    data class SetCustomerLastName(val lastName: String): CustomerEvent

    data class SetCustomerAddress(val address: String): CustomerEvent

    data class SetCustomerContactNum(val contactNum: String): CustomerEvent

    // Showing the dialog window event
    object ShowDialog: CustomerEvent


    // Hiding the dialog window event
    object HideDialog: CustomerEvent


    // Sort the restaurants event, triggered when user interacts with sorting components
    data class SortCustomer(val sortType: SortType): CustomerEvent


    // Delete the restaurants event, triggered when the user wants to delete a Restaurant
    data class DeleteCustomer(val customer: Customer): CustomerEvent
}