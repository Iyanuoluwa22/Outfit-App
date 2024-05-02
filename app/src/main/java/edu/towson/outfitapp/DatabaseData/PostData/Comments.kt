package edu.towson.outfitapp.DatabaseData.PostData

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "comments",
    foreignKeys = [ForeignKey(
        entity = Post::class,
        parentColumns = ["postId"],
        childColumns = ["postId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val commentId: Int = 0,
    val postId: Int, // Foreign key to the Post entity
    val commentText: String
)