package edu.towson.outfitapp.profile

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.*
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.*
import edu.towson.outfitapp.MainPage.ImageCard

// This view a user function is a page where a user can view another users account and posts they have made
// it is similar to the UserProfileScreen. The only difference is that you have an option to press back and go to the previous page
// The viewing user is grabbed from the UserSearch page where a user is looked up and clicked
@Composable
fun ViewAUser(navController: NavController, userViewModel: UserViewModel, postViewModel: PostViewModel) {
    val user by userViewModel.viewingUser.observeAsState()
    val allPosts by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    val userPosts = allPosts.filter { post -> post.userEmail == user?.userEmail }
    var showProgress by remember { mutableStateOf(false) }
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
                    user?.let { Username(it) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { showProgress = true },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Back")
                }
                if (showProgress) {
                    ShowProgressIndicator(navController, popBack = true)
                }
            }

            // this column displays the photos in a grid
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


@Preview
@Composable
fun PreviewViewAUser(){
    val dummyUser = User("test@gmail.com", "test", "gang", "gang", "gang")
    val userLiveData = MutableLiveData<User>()
    userLiveData.value = dummyUser
    val applicationContext = androidx.compose.ui.platform.LocalContext.current.applicationContext
    val application = applicationContext as Application // Cast the context to an Application
    val dummyUserViewModel = UserViewModel(application)
    dummyUserViewModel.setCurrentUser(userLiveData)
   // ViewAUser(rememberNavController(), userViewModel = dummyUserViewModel)
}