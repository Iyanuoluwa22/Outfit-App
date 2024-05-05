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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController, userViewModel: UserViewModel) {
    val mainUser by userViewModel.mainUser.observeAsState()
    Scaffold(topBar = {
        TopAppBar(
            modifier = Modifier.heightIn(10.dp,200.dp),
            colors = topAppBarColors(
                containerColor = Color.Cyan,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = "Outfitify",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(20.dp),
                    color = Color.White,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center
                )
            },
            actions = {
                IconButton(
                    onClick = { navController.navigate("accountSettings") },
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Color.White
                    )
                }
            }
        )
    },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
               theBottomBar(navController)
            }
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                userBio()
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
fun UploadPhotoButton() {
    IconButton(
        onClick = {},
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

@Composable
private fun theBottomBar(navController : NavController){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly){
        Column {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Upload Photo",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            IconButton(
                onClick = { navController.navigate("userFeed")},
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Explore Page",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            UploadPhotoButton()
        }
        Column {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "User Notifications",
                    Modifier.size(50.dp)
                )
            }
        }
        Column {
            IconButton(
                onClick = {
                    navController.navigate("userProfile")
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Page",
                    Modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
fun userBio(){
    Text(text = "")
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
