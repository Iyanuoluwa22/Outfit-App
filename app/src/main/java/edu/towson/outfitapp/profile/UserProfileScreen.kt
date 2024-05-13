package edu.towson.outfitapp.profile


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController, userViewModel: UserViewModel, postViewModel: PostViewModel) {
    val mainUser by userViewModel.mainUser.observeAsState()
    val allPosts by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    val userPosts = allPosts.filter { post -> post.userEmail == mainUser?.userEmail }
    Scaffold(
        topBar = { TheTopBar(navController = navController) },
        bottomBar = { TheBottomBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0.914f, 0.898f, 0.898f, 1.0f))
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfilePicture()
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    mainUser?.let { Username(it) }
                }
            }

            Column {
                LazyVerticalGrid(modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Adaptive(168.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                ){
                        items(userPosts) { post ->
                            var painter = rememberAsyncImagePainter(model = post.postUrl)
                            Image(
                                painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .aspectRatio(1f) // Ensure square aspect ratio
                            )
                        }
                }
            }
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
    //UserProfileScreen(navController = rememberNavController(), userViewModel = dummyUserViewModel)
}
