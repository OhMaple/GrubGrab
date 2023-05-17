package com.example.grubgrab.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomerViewModel (
    private val dao: CustomerDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FNAME_ASC)

    private val _customers = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.FNAME_ASC -> dao.getCustomerOrderedByFNameASC()
                SortType.FNAME_DESC -> dao.getCustomerOrderedByFNameDESC()
                SortType.LNAME_ASC -> dao.getCustomerOrderedByLNameASC()
                SortType.LNAME_DESC -> dao.getCustomerOrderedByLNameDESC()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CustomerState())
    val state = combine(_state, _sortType, _customers) {state, sortType, customers ->
        state.copy(
            customers = customers,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CustomerState())

    fun onEvent(event: CustomerEvent) {
        when(event) {
            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    dao.deleteCustomer(event.customer)
                }
            }

            CustomerEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCustomer = false
                ) }
            }

            CustomerEvent.SaveCustomer -> {
                val firstName = state.value.customerFirstName
                val lastName = state.value.customerLastName
                val address = state.value.address
                val contactNum = state.value.contactNum

                if(firstName.isBlank() || lastName.isBlank() || address.isBlank() || contactNum.isBlank()) {
                    return
                }

                val customer = Customer(
                    firstName = firstName,
                    lastName = lastName,
                    address = address,
                    contactNum = contactNum
                )
                viewModelScope.launch {
                    dao.upsertCustomer(customer)
                }
                _state.update { it.copy(
                    isAddingCustomer = false,
                    customerFirstName = "",
                    customerLastName = "",
                    address = "",
                    contactNum = ""
                ) }
            }

            is CustomerEvent.SetCustomerFirstName -> {
                _state.update { it.copy(
                    customerFirstName = event.firstName
                ) }
            }

            is CustomerEvent.SetCustomerLastName -> {
                _state.update { it.copy(
                    customerLastName = event.lastName
                ) }
            }

            is CustomerEvent.SetCustomerAddress -> {
                _state.update {it.copy(
                    address = event.address
                )}
            }

            is CustomerEvent.SetCustomerContactNum -> {
                _state.update { it.copy(
                    contactNum = event.contactNum
                ) }
            }

            CustomerEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingCustomer = true
                ) }
            }

            is CustomerEvent.SortCustomer -> {
                _sortType.value = event.sortType
            }
        }
    }
}
