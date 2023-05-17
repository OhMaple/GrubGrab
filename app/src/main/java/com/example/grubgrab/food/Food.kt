package com.example.grubgrab.food

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    val foodName: String,
    val price: Float,
    @PrimaryKey(autoGenerate = true)
    val FoodID: Int = 0
)
