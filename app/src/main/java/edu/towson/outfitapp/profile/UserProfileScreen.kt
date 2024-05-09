package edu.towson.outfitapp.profile


import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController, userViewModel: UserViewModel) {
    val mainUser by userViewModel.mainUser.observeAsState()
    Scaffold(topBar = {
        TheTopBar(navController)
    },
        bottomBar = {
            TheBottomBar(navController)
        }) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .background(color = Color(0.914f, 0.898f, 0.898f, 1.0f))
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                ProfilePicture()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                mainUser?.let { Username(it) }
            }


            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                mainUser?.let { FollowersCount(it) }
                mainUser?.let { FollowingCount(it) }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Composable
fun ProfilePicture() {
    // Replace with image resource
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Profile Picture of user",
        Modifier.size(50.dp)
    )
}

@Composable
fun Username(mainUser: User) {
    Text(text = mainUser.userName, color = Color.Black,
        fontFamily = FontFamily.Monospace)
}

@Composable
fun FollowersCount(mainUser: User) {
    Text("Followers: 0", color = Color.Black,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun FollowingCount(mainUser: User) {
    Text("Following: 0", color = Color.Black,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun UploadPhotoButton(navController: NavController) {
    IconButton(
        onClick = {navController.navigate("imageUpload")},
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Upload Photo",
            Modifier.size(50.dp)
        )
    }
}


@Preview
@Composable
fun PreviewUserProfileScreen() {
    val dummyUser = User("test@gmail.com", "test", "gang", "gang", "gang")
    val userLiveData = MutableLiveData<User>()
    userLiveData.value = dummyUser
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    dummyUserViewModel.setCurrentUser(userLiveData)
    UserProfileScreen(navController = rememberNavController(), userViewModel = dummyUserViewModel)
}
