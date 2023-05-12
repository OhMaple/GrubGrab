package com.example.grubgrab

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Upsert // Adds if doesn't exist, updates if it does
    suspend fun upsertRestaurant(restaurant: Restaurant)

    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurant ORDER BY restaurantName ASC")
    fun getRestaurantOrderedByNameASC(): Flow<List<Restaurant>> // Flow keeps it updated live/if there's any changes

    @Query("SELECT * FROM restaurant ORDER BY restaurantName DESC")
    fun getRestaurantOrderedByNameDESC(): Flow<List<Restaurant>> // Flow keeps it updated live/if there's any changes
}