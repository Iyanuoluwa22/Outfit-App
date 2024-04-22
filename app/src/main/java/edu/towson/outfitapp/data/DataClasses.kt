package edu.towson.outfitapp.data

// classes of data that we are going to use to show user info before we have the database ...


data class Post(
    var Id: Int,
    var userCaption: String,
    var PostingUsername: String,
    var imageId: Int?,
    var comments: MutableList<Comment>?
)

data class Comment(
    var commentText : String,
    var commentUser : User,
)

// dummy data to show on the screen.
object DummyData {
    val dummyUsers = listOf(
        User("Kevin","Kevin", "Kevin", "S","ksamim1@towson.edu"),
    )

    val Posts = listOf(
        Post(1, "Developing some Apps today!", "Kevin", 0,null),
        Post(2, "The weather is pretty good today.", "Kevin", 1, null),
        Post(3, "NBA Playoffs are starting this weekend!", "Kevin", 2,null)
    )
}