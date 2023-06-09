package com.example.grubgrab.restaurant

// Import statements
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.grubgrab.restaurant.Restaurant
import com.example.grubgrab.restaurant.RestaurantDao

// Annotating the Room Database 'RestaurantDatabase', with 1 entity 'Restaurant' and version 1
@Database(
    entities = [Restaurant::class],
    version = 1
)
// Abstract Class that extends RoomDatabase:
abstract class RestaurantDatabase : RoomDatabase() {
    // Declaring abstract value for the RestaurantDao interface
    abstract val dao: RestaurantDao
}