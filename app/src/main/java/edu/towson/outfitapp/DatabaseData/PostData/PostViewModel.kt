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


    private val postDao: PostDao
    private val allPost: LiveData<List<Post>>

    init {
        val database = UserDatabase.getDatabase(application)
        postDao = database.postDao()
        allPost = postDao.readAllPostData()
    }

    fun getAllPosts(): LiveData<List<Post>> {
        return postDao.readAllPostData()
    }

    fun getPostUrl(): LiveData<List<String>> {
        return postDao.readPostUrl()
    }

    fun addPost(post: Post) {
        viewModelScope.launch {
            postDao.addPost(post)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            postDao.deletePost(post)
        }
    }

    fun deleteAllPost() {
        viewModelScope.launch {
            postDao.deleteAllPost()
        }
    }

    fun getCurrentUserPosts(email: String){
        viewModelScope.launch {
            postDao.getPostsByUserEmail(email)
        }
    }

    fun likePost(context: Context, postid: Int){
        viewModelScope.launch {
            postDao.addLikeToPost(postid)
            createNotificationChanel(context)
        }
    }

    fun unLikePost(postId: Int ){
        viewModelScope.launch {
            postDao.unLikeToPost(postId)

        }
    }

    fun addComment(context: Context, postid: Int){
        viewModelScope.launch {
            // Add comment to comments of the called post.
            // DisplayNotifications(context, "You have added a comment!", "Commented")

        }
    }
}