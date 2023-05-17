package com.example.grubgrab

// Import statements
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.compose.GrubGrabTheme
import com.example.grubgrab.restaurant.RestaurantDatabase
import com.example.grubgrab.restaurant.RestaurantScreen
import com.example.grubgrab.restaurant.RestaurantViewModel

// MainActivity Class
class MainActivity : AppCompatActivity() {

    // Database instance
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RestaurantDatabase::class.java,
            "restaurants.db"
        ).build()
    }

    // View Model instance
    private val viewModel by viewModels<RestaurantViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RestaurantViewModel(db.dao) as T
                }
                }
            }
    )


    // onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrubGrabTheme {
                val state by viewModel.state.collectAsState()
                RestaurantScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}