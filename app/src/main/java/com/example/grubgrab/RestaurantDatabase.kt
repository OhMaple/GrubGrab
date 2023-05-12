package com.example.grubgrab

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Restaurant::class],
    version = 1
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val dao: RestaurantDao
}