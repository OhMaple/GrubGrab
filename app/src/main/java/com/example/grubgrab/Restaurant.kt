package com.example.grubgrab

// Import statements
import androidx.room.Entity
import androidx.room.PrimaryKey

// Initialising 'Restaurant' entity and attributes
// Auto incrementing primary key of 'id'
@Entity
data class Restaurant(
    val restaurantName: String,
    val contactNum: String,
    val address: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
