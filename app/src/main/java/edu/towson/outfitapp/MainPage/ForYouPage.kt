package edu.towson.outfitapp.MainPage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

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
        User(1, "Nu"),
        User(1, "Christian")
    )
}


@Composable
fun MainPage (userId : Int){ // User ID passed is the user that is currently logged into the system.

}

