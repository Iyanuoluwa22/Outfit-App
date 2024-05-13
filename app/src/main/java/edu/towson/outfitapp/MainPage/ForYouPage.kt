package edu.towson.outfitapp.MainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.DatabaseData.PostData.PostViewModel
import edu.towson.outfitapp.DatabaseData.UserData.User
import edu.towson.outfitapp.DatabaseData.UserData.UserViewModel
import edu.towson.outfitapp.HelperFunctions.TheBottomBar
import edu.towson.outfitapp.HelperFunctions.TheTopBar
import edu.towson.outfitapp.data.DummyData
import edu.towson.outfitapp.data.Post

@Composable
fun ForYouPage(navController: NavController, userViewModel: UserViewModel, postViewModel: PostViewModel) {
    val context = LocalContext.current
    // show all posts
    val postsState by postViewModel.getAllPosts().observeAsState(initial = emptyList())
    Scaffold(topBar = {
        TheTopBar(navController)
    },
        bottomBar = {
            TheBottomBar(navController = navController)
        }
    ) { innerPadding ->


        LazyColumn(
            modifier = Modifier.padding(innerPadding)
                .background(Color.Black)
        ){
            items(postsState) { post ->
                ImageCard(post = post, userViewModel = userViewModel , postViewModel = postViewModel, context)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }

    }


}





