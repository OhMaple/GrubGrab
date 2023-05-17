package com.example.grubgrab.restaurant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddRestaurantDialog(
    state: RestaurantState,
    onEvent: (RestaurantEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onEvent(RestaurantEvent.HideDialog)
        },
        title = { Text(text = "Add restaurant") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.restaurantName,
                    onValueChange = {
                        onEvent(RestaurantEvent.SetRestaurantName(it))
                    },
                    placeholder = {
                        Text(text = "Restaurant name")
                    }
                )

                TextField(
                    value = state.address,
                    onValueChange = {
                        onEvent(RestaurantEvent.SetRestaurantAddress(it))
                    },
                    placeholder = {
                        Text(text = "Restaurant address")
                    }
                )

                TextField(
                    value = state.contactNum,
                    onValueChange = {
                        onEvent(RestaurantEvent.SetRestaurantContactNum(it))
                    },
                    placeholder = {
                        Text(text = "Restaurant contact number")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(RestaurantEvent.SaveRestaurant)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}