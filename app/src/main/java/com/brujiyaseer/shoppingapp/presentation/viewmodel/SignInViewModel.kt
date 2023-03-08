package com.brujiyaseer.shoppingapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brujiyaseer.shoppingapp.data.local.entities.User
import com.brujiyaseer.shoppingapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun saveUser(firstName: String, lastName: String, email: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(firstName, lastName, email)
            user.uid = 1
            repository.saveUser(user)
        }

    fun getLoggedInUser() = repository.getUser()

    fun deleteUser() = viewModelScope.launch {
        repository.deleteUser()
    }
}