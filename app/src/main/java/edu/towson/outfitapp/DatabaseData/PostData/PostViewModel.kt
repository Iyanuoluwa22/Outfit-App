package edu.towson.outfitapp.DatabaseData.PostData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.towson.outfitapp.DatabaseData.UserData.UserDatabase
import kotlinx.coroutines.launch

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

    fun getCurrentUserPosts(email: String) : LiveData<List<Post?>?>{
        return postDao.getPostsByUserEmail(email)
    }

    fun likePost(postid: Int){
        viewModelScope.launch {
            postDao.addLikeToPost(postid)
        }
    }

    fun unLikePost(postId: Int ){
        viewModelScope.launch {
            postDao.unLikeToPost(postId)
        }
    }


}