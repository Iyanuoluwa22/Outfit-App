package edu.towson.outfitapp.DatabaseData.UserData

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val userDao: UserDao
    private val allUsers: LiveData<List<User?>?>
    private val _mainUser = MutableLiveData<User?>(null)
    private val _viewingUser = MutableLiveData<User?>(null)
    val mainUser: LiveData<User?> = _mainUser
    val viewingUser : LiveData<User?>  = _viewingUser

    init {
        val database = UserDatabase.getDatabase(application)
        userDao = database.userDao()
        allUsers = userDao.readAllData()     // Starts off by getting all the current data within the database

        allUsers.observeForever { users ->
            Log.d("UserViewModel", "Number of users: ${users?.size}")
        }
    }

    fun getAllUsers(): LiveData<List<User?>?> {
        return allUsers
    }

    fun setCurrentUser(userLiveData: LiveData<User?>) {  // Allows us to know who the current user is
        viewModelScope.launch {
            // Observe the LiveData and get the value when it changes
            userLiveData.observeForever { user ->  // uses live data so we can constantly know how the current user login  is
                // Update the value of _mainUser with the observed user value
                _mainUser.postValue(user)
            }
        }
    }

    fun setViewingUser(userLiveData: LiveData<User?>){  // Lets know who's page we are looking at after
        viewModelScope.launch {
            // Observe the LiveData and get the value when it changes
            userLiveData.observeForever { user ->
                // Update the value of _viewingUser with the observed user value
                _viewingUser.postValue(user)
            }
        }
    }

    fun resetViewingUser(){
        _viewingUser.removeObserver { }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.addUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }

    fun deleteAllUsers() { // Please don't use this unless you have to
        viewModelScope.launch {
            userDao.deleteAllUsers()
        }
    }

    fun getUserByUsername(userName: String): LiveData<User?> {
        return userDao.getUserByUsername(userName)
    }

    fun loginCheck(userName: String, password: String): LiveData<User?> {
        return userDao.login(userName, password)
    }

    fun getUserName(userEmail:String): LiveData<String?>{
        return userDao.getUsernameByEmail(userEmail)
    }

    fun getAllUsersWithPrefix(userName:String) : LiveData<List<User?>?>{
        return userDao.getUsersByUsernamePrefix(userName)
    }

}
