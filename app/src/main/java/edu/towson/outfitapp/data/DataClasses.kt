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
        User("David","David", "Kevin", "T","david@towson.edu"),
        User("Christian","Christian", "Christian", "C","christian@towson.edu")
    )

    val Posts = listOf(
        Post(1, "Developing some Apps today!", "Kevin", 0,null),
        Post(2, "The weather is pretty good today.", "Christian", 1, null),
        Post(3, "Cant wait for the party.", "David", 2,null),
        Post(4, "Are you guys going out tonight?", "David", 2,null),
        Post(5, "Party fit", "Sully", 2,null),
        Post(6, "Call of duty is not as good as it used to be :( ", "Christian", 2,null),
        Post(7, "This is the best pizza in towson", "Christian", 2,null),
        Post(8, "Anyone going to the mall?", "David", 2,null),
        Post(9, "What classes are yall taking next semester?", "Kevin", 2,null),
        Post(10, "Watching friends right now.", "Kevin", 2,null)
    )
}