package com.example.grubgrab.customer

import androidx.room.Database
import androidx.room.RoomDatabase

// Annotating the Room Database 'CustomerDatabase', with 2 entities, 'Customer' and 'Order' and version 1
@Database(
    entities = [Customer::class],
    version = 1
)
// Abstract Class that extends RoomDatabase:
abstract class CustomerDatabase : RoomDatabase() {
    // Declaring abstract value for the CustomerDao interface
    abstract val dao: CustomerDao
}