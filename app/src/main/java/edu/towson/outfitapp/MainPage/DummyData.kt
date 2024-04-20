package edu.towson.outfitapp.MainPage

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