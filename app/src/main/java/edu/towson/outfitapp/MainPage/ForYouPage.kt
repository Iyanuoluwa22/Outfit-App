package edu.towson.outfitapp.MainPage

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

// classes of data that we are going to use to show user info before we have the database ...
data class User(
    val UserId: Int,
    val UserName: String
)

data class Post(
    val Id: Int,
    val Caption: String,
    val PostingUserId: Int
)

// dummy data to show on the screen.
object DummyData {
    val Users = listOf(
        User(1, "Kevin"),
        User(2, "Nu"),
        User(3, "Christian")
    )

    val Posts = listOf(
        Post(1, "Developing some Apps today!", 1),
        Post(2, "The weather is pretty good today.", 2),
        Post(3, "NBA Playoffs are starting this weekend!", 2)
    )
}

@Preview
@Composable
fun PagePreview(){
    MainPage(CurruserId = 3)
}

@Composable
fun TopBar(){
    Surface(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(text = "Top Bar", modifier = Modifier.padding(15.dp))
    }
}


@Composable
fun MainPage (CurruserId : Int){ // User ID passed is the user that is currently logged into the system.
    // show all of the posts that are not made by the current user.
    val ShownPosts = DummyData.Posts.filter { it.PostingUserId != CurruserId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){
        TopBar()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ){
            items(ShownPosts) { post ->
                Posted(post)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}

@Composable
fun Posted(post: Post){
    Surface(
      modifier = Modifier.fillMaxWidth(), color = Color.LightGray
    ){
        Text(
            text = post.Caption,
            modifier = Modifier.padding(15.dp)
        )
    }
}

