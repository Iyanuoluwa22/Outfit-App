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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.towson.outfitapp.data.DummyData
import edu.towson.outfitapp.data.Post
import edu.towson.outfitapp.data.User
import edu.towson.outfitapp.viewmodel.UserViewModelF

@Composable
fun ForYouPage(navController: NavController, userViewModelF: UserViewModelF){
    // show all of the posts that are not made by the current user.
    val mainUser by userViewModelF.mainUser.collectAsState()
    val shownPosts = DummyData.Posts.filter { it.PostingUsername != mainUser?.username }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ){
        TopBar(navController)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ){
            items(shownPosts) { post ->
                val user = DummyData.dummyUsers.find{it.username == post.PostingUsername }
                user?.let {Posted(post, it)}
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

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
fun Posted(post: Post, user: User){
    Surface(
        modifier = Modifier.fillMaxWidth(), color = Color.LightGray
    ){
        Column(
            modifier = Modifier.padding(20.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "@${user.username}",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.labelSmall,
                )
                Row{
                    IconButton(
                        onClick = { /*Handle the clicking*/ },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        onClick = { /*Handle the clicking*/ },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(Icons.Default.AddCircle, contentDescription = "Comment")
                    }
                }
            }
            Text(
                text = post.userCaption,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview
@Composable
fun PreviewForYouPageScreen(){
    val dummyUser = User("test", "test123", "John", "Doe", "john.doe@example.com")
    val dummyUserViewModelF = UserViewModelF().apply {
        setUser(dummyUser)
    }
    ForYouPage(navController = rememberNavController(), dummyUserViewModelF)
}


