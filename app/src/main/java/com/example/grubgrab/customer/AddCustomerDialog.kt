package com.example.grubgrab.customer

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
fun AddCustomerDialog(
    state: CustomerState,
    onEvent: (CustomerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
            onEvent(CustomerEvent.HideDialog)
        },
        title = { Text(text = "Add customer") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.customerFirstName,
                    onValueChange = {
                        onEvent(CustomerEvent.SetCustomerFirstName(it))
                    },
                    placeholder = {
                        Text(text = "Customer's First Name")
                    }
                )

                TextField(
                    value = state.customerLastName,
                    onValueChange =  {
                        onEvent(CustomerEvent.SetCustomerLastName(it))
                    },
                    placeholder = {
                        Text(text = "Customer's Last Name")
                    }
                )

                TextField(
                    value = state.address,
                    onValueChange = {
                        onEvent(CustomerEvent.SetCustomerAddress(it))
                    },
                    placeholder = {
                        Text(text = "Customer's Address")
                    }
                )

                TextField(
                    value = state.contactNum,
                    onValueChange = {
                        onEvent(CustomerEvent.SetCustomerContactNum(it))
                    },
                    placeholder = {
                        Text(text = "Customer's Contact Number")
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
                    onEvent(CustomerEvent.SaveCustomer)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}