package com.example.grubgrab.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Upsert
    suspend fun upsertCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Query("SELECT * FROM customer ORDER BY firstName ASC")
    fun getCustomerOrderedByFNameASC(): Flow<List<Customer>>

    @Query("SELECT * FROM customer ORDER BY firstName DESC")
    fun getCustomerOrderedByFNameDESC(): Flow<List<Customer>>

    @Query("SELECT * FROM customer ORDER BY lastName ASC")
    fun getCustomerOrderedByLNameASC(): Flow<List<Customer>>

    @Query("SELECT * FROM customer ORDER BY lastName DESC")
    fun getCustomerOrderedByLNameDESC(): Flow<List<Customer>>
}