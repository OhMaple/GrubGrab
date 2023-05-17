package com.example.grubgrab.order

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Order(
    val total: Float,
    @PrimaryKey(autoGenerate = true)
    val OrderID: Int = 0,
    // Foreign key for CustID
    // Foreign key for FoodID
    // Foreign key for RestID
)
