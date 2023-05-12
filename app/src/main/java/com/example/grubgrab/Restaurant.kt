package com.example.grubgrab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    val restaurantName: String,
    val contactNum: String,
    val address: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
