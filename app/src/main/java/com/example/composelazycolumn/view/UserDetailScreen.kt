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
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.navigation.compose.*
import com.example.composelazycolumn.viewModel.UserViewModel

@Composable
fun UserDetailScreen(viewModel: UserViewModel) {
    val user by viewModel.selectedUser.collectAsState()

    if (user == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No user selected")
        }
    } else {
        Column(
            Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(color = Color.LightGray)
        ) {
            Column (
                Modifier.padding(start = 12.dp)
            ){
                Spacer(modifier = Modifier.height(80.dp))
                Text("Name: ${user!!.username}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
//                Text("bio: ${user!!.bio}")
//                Spacer(modifier = Modifier.height(8.dp))
                Text("password: ${user!!.private_key}")
                Spacer(modifier = Modifier.height(8.dp))
                Text("account created at: ${user!!.created_at}")
//                Text("Address:")
//                Text("${user!!.address.street}, ${user!!.address.suite}")
//                Text("${user!!.address.city} - ${user!!.address.zipcode}")
            }
        }
    }
}
