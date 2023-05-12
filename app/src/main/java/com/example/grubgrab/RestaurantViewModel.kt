package com.example.grubgrab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class RestaurantViewModel(
    private val dao: RestaurantDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.NAME_ASC)

    private val _restaurants = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.NAME_ASC -> dao.getRestaurantOrderedByNameASC()
                SortType.NAME_DESC -> dao.getRestaurantOrderedByNameDESC()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(RestaurantState())
    val state = combine(_state, _sortType, _restaurants) {state, sortType, restaurants ->
        state.copy(
            restaurants = restaurants,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RestaurantState())

    fun onEvent(event: RestaurantEvent) {
        when(event) {
            is RestaurantEvent.DeleteRestaurant -> {
                viewModelScope.launch {
                    dao.deleteRestaurant(event.restaurant)
                }
            }

            RestaurantEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingRestaurant = false
                ) }
            }

            RestaurantEvent.SaveRestaurant -> {
                val restaurantName = state.value.restaurantName
                val restaurantAddress = state.value.address
                val restaurantContactNumber = state.value.contactNum

                if(restaurantName.isBlank() || restaurantAddress.isBlank() || restaurantContactNumber.isBlank()) {
                    return
                }

                val restaurant = Restaurant(
                    restaurantName = restaurantName,
                    address = restaurantAddress,
                    contactNum = restaurantContactNumber
                )
                viewModelScope.launch {
                    dao.upsertRestaurant(restaurant)
                }
                _state.update { it.copy(
                    isAddingRestaurant = false,
                    restaurantName = "",
                    address = "",
                    contactNum = ""
                ) }
            }

            is RestaurantEvent.SetRestaurantAddress -> {
                _state.update { it.copy(
                    address = event.address
                ) }
            }

            is RestaurantEvent.SetRestaurantContactNum -> {
                _state.update { it.copy(
                    contactNum = event.contactNum
                ) }
            }

            is RestaurantEvent.SetRestaurantName -> {
                _state.update {it.copy(
                    restaurantName = event.restaurantName
                )}
            }

            RestaurantEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingRestaurant = true
                ) }
            }

            is RestaurantEvent.SortRestaurants -> {
                _sortType.value = event.sortType
            }
        }
    }
}