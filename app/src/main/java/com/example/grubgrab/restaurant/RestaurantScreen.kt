package com.example.grubgrab.restaurant
// Import statements
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function 'RestaurantScreen'
@Composable
fun RestaurantScreen(
    state: RestaurantState,  // State of the Restaurant application
    onEvent: (RestaurantEvent) -> Unit  // Lambda function for handling RestaurantEvents
) {
    // Scaffold Composable that provides a basic layout for the screen
    Scaffold(
        floatingActionButton = {
            // Floating Action Button for adding new Restaurants
            FloatingActionButton(onClick = {
                onEvent(RestaurantEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Restaurant")
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->
        // Check whether the user is adding a new Restaurant, then show AddRestaurantDialog
        if(state.isAddingRestaurant) {
            AddRestaurantDialog(state = state, onEvent = onEvent)
        }

        // LazyColumn for the list of Restaurants
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First item of the list consists of the options for sorting:
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Loop through all the sort types previously configured, and display them in Radio Buttons
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(RestaurantEvent.SortRestaurants(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(RestaurantEvent.SortRestaurants(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }

                }
            }
            // Display each Restaurant in a Row
            items(state.restaurants) { restaurant ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = restaurant.restaurantName,
                            fontSize = 20.sp
                        )
                        Text(
                            text = restaurant.address,
                            fontSize = 16.sp
                        )
                        Text(
                            text = restaurant.contactNum,
                            fontSize = 16.sp
                        )
                    }
                    // IconButton for deleting a Restaurant
                    IconButton(onClick = {
                        onEvent(RestaurantEvent.DeleteRestaurant(restaurant))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Restaurant"
                        )
                    }
                }
            }
        }
    }
}
