package com.example.composelazycolumn.viewModel

import androidx.lifecycle.ViewModel
import com.example.composelazycolumn.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    fun selectUser(user: User) {
        _selectedUser.value = user
    }
}
