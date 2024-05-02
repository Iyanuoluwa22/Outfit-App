package edu.towson.outfitapp.DatabaseData.PostData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @Insert
    suspend fun addPost(post: Post) // Add a new Post

    @Query("SELECT * FROM userPosts ")
    fun readAllPostData(): LiveData<List<Post>> // Get all post from table

    @Query("SELECT postUrl FROM userPosts")
    fun readPostUrl(): LiveData<List<String>>  // Gets all urls from table

    @Delete
    suspend fun deletePost(post: Post)  // Deletes a post

    @Query("DELETE FROM userPosts") //Deletes all post from table (Please don't use this unless uou have too)
    suspend fun deleteAllPost()

    @Query("SELECT * FROM userPosts WHERE userID = :userId")
    suspend fun getPostsByUserId(userId: Int): List<Post> // This gets a particular list of post based on the userID

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getCommentsForPost(postId: Int): LiveData<List<Comment>>


}