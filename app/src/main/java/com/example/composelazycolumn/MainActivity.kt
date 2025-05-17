package com.example.composelazycolumn

import UserDetailScreen
import InsertUserForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelazycolumn.ui.theme.ComposeLazyColumnTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.composelazycolumn.model.User
import com.example.composelazycolumn.service.RetrofitInstance
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.graphics.Color as Colour
import com.example.composelazycolumn.viewModel.UserViewModel
import androidx.activity.viewModels
import androidx.navigation.compose.*
import android.app.Activity
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import com.example.composelazycolumn.view.RegisterUserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userViewModel by viewModels<UserViewModel>()

        setContent {
            ComposeLazyColumnTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        NameListScreen(
                            onUserClick = { user ->
                                userViewModel.selectUser(user)
                                navController.navigate("detail")
                            }
                        )
                    }
                    composable("detail") {
                        UserDetailScreen(userViewModel)
                    }
                    composable("register") {
                        RegisterUserScreen(userViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun NameListScreen(onUserClick: (User) -> Unit) {
    UpdateStatusBarColor(isLightBackground = true)

    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            users = RetrofitInstance.apiService.getUsers()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Loading...")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            items(users) { user ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            onUserClick(user)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "User Icon",
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 12.dp),
                            tint = Color.Gray
                        )

                        Column {
                            Text(text = user.id)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = user.username, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun UpdateStatusBarColor(isLightBackground: Boolean) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = if (isLightBackground) Colour.WHITE else Colour.BLACK //warna bg putih/hitam
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLightBackground //warna ikon light/dark
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NameListScreenPreview() {
//    ComposeLazyColumnTheme {
//        NameListScreen()
//    }
//}
