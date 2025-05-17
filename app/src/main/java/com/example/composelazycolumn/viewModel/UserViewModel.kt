package com.example.composelazycolumn.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelazycolumn.model.User
import com.example.composelazycolumn.model.InsertUserResponse
import com.example.composelazycolumn.service.InsertUserService
import com.example.composelazycolumn.service.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    fun insertUserToServer(username: String, privateKey: String, email: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.insertUserService.insertUser(username, privateKey, email)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.success == true) {
                        Log.d("Insert", "Sukses: ${body.message}")
                    } else {
                        Log.e("Insert", "Gagal: ${body?.error}")
                    }
                } else {
                    Log.e("Insert", "Error response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Insert", "Exception: ${e.message}")
            }
        }
    }
}
