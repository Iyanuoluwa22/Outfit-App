package edu.towson.outfitapp.DatabaseData.PostData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.towson.outfitapp.DatabaseData.UserData.UserDatabase
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import edu.towson.outfitapp.MainPage.createNotificationChanel

class PostViewModel (application: Application): AndroidViewModel(application) {

  // ViewModel for Posts
    private val postDao: PostDao
    private val allPost: LiveData<List<Post>>

    init {
        val database = UserDatabase.getDatabase(application)
        postDao = database.postDao()
        allPost = postDao.readAllPostData()
    }

    fun getAllPosts(): LiveData<List<Post>> {  // This function gets all the posts from all users, using live data
        return postDao.readAllPostData()
    }

    fun getPostUrl(): LiveData<List<String>> {  // An un used function that returns a list of all the post's in the databases
        return postDao.readPostUrl()
    }

    fun addPost(post: Post) { // Adds a post to the database, while making sure the post is tied to the user through a foreign key
        viewModelScope.launch {
            postDao.addPost(post)
        }
    }

    fun deletePost(post: Post) { // Deletes a post (removes it from data base)
        viewModelScope.launch {
            postDao.deletePost(post)
        }
    }

    fun deleteAllPost() { //  Deletes all posts from the data (users are still kept)
        viewModelScope.launch {
            postDao.deleteAllPost()
        }
    }

    fun getCurrentUserPosts(email: String){  // Gets the all posts tied to the user based on email
        viewModelScope.launch {
            postDao.getPostsByUserEmail(email)
        }
    }

    fun likePost(context: Context, postid: Int){ // Updates the like post variable
        viewModelScope.launch {
            postDao.addLikeToPost(postid)
            createNotificationChanel(context)
        }
    }

    fun unLikePost(postId: Int ){ // Also updates the like post variable
        viewModelScope.launch {
            postDao.unLikeToPost(postId)

        }
    }

    fun addComment(context: Context, postid: Int){       // Lets users add a comment to a post and comments and posts are tied to using a foreign key
        viewModelScope.launch {
            // Add comment to comments of the called post.
            // DisplayNotifications(context, "You have added a comment!", "Commented")

        }
    }
}