// di file com/example/composelazycolumn/view/RegisterUserScreen.kt
package com.example.composelazycolumn.view

import androidx.compose.runtime.Composable
import com.example.composelazycolumn.viewModel.UserViewModel
import InsertUserForm

@Composable
fun RegisterUserScreen(viewModel: UserViewModel) {
    InsertUserForm { username, privateKey, email ->
        viewModel.insertUserToServer(username, privateKey, email)
    }
}
