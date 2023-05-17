package com.example.grubgrab.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

// Initialising 'Customer' entity and attributes
// Auto incrementing primary key of 'id'
@Entity
data class Customer(
    val firstName: String,
    val lastName: String,
    val address: String,
    val contactNum: String,
    @PrimaryKey(autoGenerate = true)
    val CustID: Int = 0
)
