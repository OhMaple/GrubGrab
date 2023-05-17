# GrubGrab 🍝
_Complex Systems Coursework 2 Project by Jasmin Lord (24623458) and Lucy Williamson (24522716) for Edge Hill University - BSc Computer Science_

## About the app 🥘
GrubGrab is an Android app created using Kotlin and Jetpack Compose that allows users to order food from their favourite restaurants from their phone!

## Functionality of Each File 🍔
The ***MainActivity*** class is responsible for setting up the main activity of the app. It initializes the database and the view model, and sets the content of the screen using Jetpack Compose.

***AddRestaurantDialog*** is a composable function used to create an AlertDialog when the user presses the floating action button to create a new Restaurant.  

The ***Restaurant*** data class is where the entity 'Restaurant' is initialised. It has three attributes: 'restaurantName', 'contactNum' and 'address' which are all Strings. The primary key is an auto-incrementing Int, 'id'.

***RestaurantDao*** is the Data Access Object (DAO) interface responsible for managing data within the 'Restaurant' entity within GrubGrab and defines the methods and operations performed on the 'Restaurant' table and entity within the database.

The ***RestaurantDatabase*** is a Room Database that manages the 'Restaurant' entity within the GrubGrab application. This essentially links the application to a back-end local SQLite database 

The ***RestaurantEvent*** file contains the 'RestaurantEvent' sealed interface, which defines all the possible events related to the management of the Restaurants within the application.
### Events:
- _SaveRestaurant:_ An event triggered when the user saves a new restaurant or updates an existing one.
- _SetRestaurantName:_ An event triggered when the user sets or updates the name of a restaurant.
- _SetRestaurantContactNum:_ An event triggered when the user sets or updates the contact number of a restaurant.
- _SetRestaurantAddress:_ An event triggered when the user sets or updates the address of a restaurant.
- _ShowDialog:_ An event triggered when the user wants to display a dialog window in the UI.
- _HideDialog:_ An event triggered when the user wants to hide a dialog window in the UI.
- _SortRestaurants:_ An event triggered when the user interacts with sorting components to sort the list of restaurants by different criteria.
- _DeleteRestaurant:_ An event triggered when the user wants to delete a restaurant from the list.

The main user interface (UI) of the application is created within the ***RestaurantScreen*** composable function. It takes 'state' and 'onEvent' as arguments, with 'state' being an instance of _'RestaurantState'_ which holds the current state of restaurants being displayed, and 'onEvent' is a function that triggers a 'RestaurantEvent' when the user interacts with the UI. The UI contains a row of radio buttons to sort the restaurants and each restaurant item has a delete button which triggers the deletion of that restaurant.

***RestaurantState*** is a data class that holds the current state of the restaurants. This means RestaurantState contains a list of the Restaurants, the current sorting method being used, and the content of the fields used when adding a new Restaurant (name, contact number, address). There is also a boolean value representing whether or not the user is currently in the process of adding a new Restaurant or not.

Managing the data and user interactions within the application falls upon ***RestaurantViewModel***, which acts as a bridge between the UI layer and the database. Essentially maps a user interaction to an event/function to be performed. These events are defined in _'RestaurantEvent'_.

***SortType*** is an enum to represent the names for different sorting options such as name_asc for sorting by restaurant name in ascending order, or name_desc for sorting by restaurant name in descending order. 
