package edu.towson.outfitapp.MainPage

import android.graphics.drawable.Icon
import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// classes of data that we are going to use to show user info before we have the database ...
data class User(
    val UserId: Int,
    val UserName: String
)

data class Post(
    val Id: Int,
    val Caption: String,
    val PostingUserId: Int,
    val ImageId: Int?
)

// dummy data to show on the screen.
object DummyData {
    val Users = listOf(
        User(1, "Kevin"),
        User(2, "Nu"),
        User(3, "Christian")
    )

    val Posts = listOf(
        Post(1, "Developing some Apps today!", 1, null),
        Post(2, "The weather is pretty good today.", 2, null),
        Post(3, "NBA Playoffs are starting this weekend!", 2, null)
    )
}

@Preview
@Composable
fun PagePreview(){
    val navController = rememberNavController()
    MainPage(CurruserId = 3, controller = navController)
}

@Composable
fun TopBar(controller: NavController){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp)
        ){
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "User Profile Page",
                modifier = Modifier
                    .size(20.dp)
                    .clickable { controller.navigate("UserProfileScreen") }
                    .padding(end = 10.dp)
            )
        }
        Text(
            text = "Welcome, Christian!",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )

    }
}


@Composable
fun MainPage (CurruserId : Int, controller: NavController){ // User ID passed is the user that is currently logged into the system.
    // show all of the posts that are not made by the current user.
    val shownPosts = DummyData.Posts.filter { it.PostingUserId != CurruserId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){
        TopBar(controller)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ){
            items(shownPosts) { post ->
                val user = DummyData.Users.find{it.UserId == post.PostingUserId }
                user?.let {Posted(post, it)}
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}

@Composable
fun Posted(post: Post, user: User){
    Surface(
      modifier = Modifier.fillMaxWidth(), color = Color.LightGray
    ){
        Text(
            text = "@${user.UserName}",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.labelSmall,

        )
        Text(
            text = post.Caption,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

