package edu.towson.outfitapp.DatabaseData.UserData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: UserDao
    private val allUsers: LiveData<List<User>>

    init {
        val database = UserDatabase.getDatabase(application)
        userDao = database.userDao()
        allUsers = userDao.readAllData()
    }

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.readAllData()
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

    fun deleteAllUsers() { // Please don't use this unless you have too
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
}