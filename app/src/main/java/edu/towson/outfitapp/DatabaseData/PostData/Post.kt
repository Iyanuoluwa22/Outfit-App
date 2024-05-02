package edu.towson.outfitapp.DatabaseData.PostData

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import edu.towson.outfitapp.DatabaseData.UserData.User


@Entity(
    tableName = "userPosts",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userID"], // Corrected to match the property name in the Post entity
        onDelete = ForeignKey.CASCADE // Specify the onDelete action if needed
    )]
)
data class Post (
    val userID: Int, // foreign key
    val postUrl: String,
    val postDate: String?,
    var postCaption: String?,
    var postLikeNum: Int = 0,


    ){
    @PrimaryKey(autoGenerate = true)
    var postId: Int = 0

}

