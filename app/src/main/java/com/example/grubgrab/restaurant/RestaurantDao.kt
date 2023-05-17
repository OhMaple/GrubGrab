package com.example.grubgrab.restaurant

// Import statements
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.grubgrab.restaurant.Restaurant
import kotlinx.coroutines.flow.Flow

// Data Access Object - How data within the 'Restaurant' entity is managed
@Dao
interface RestaurantDao {

    // Inserts a new restaurant if the id doesn't exist, updates the existing one if it does
    @Upsert
    suspend fun upsertRestaurant(restaurant: Restaurant)

    // Deletes the restaurant
    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)

    // Retrieves all restaurants from the database, sorted in ascending order by restaurant name
    @Query("SELECT * FROM restaurant ORDER BY restaurantName ASC")
    fun getRestaurantOrderedByNameASC(): Flow<List<Restaurant>> // Flow keeps it updated live/if there's any changes

    // Retrieves all restaurants from the database, sorted in descending order by restaurant name
    @Query("SELECT * FROM restaurant ORDER BY restaurantName DESC")
    fun getRestaurantOrderedByNameDESC(): Flow<List<Restaurant>> // Flow keeps it updated live/if there's any changes
}