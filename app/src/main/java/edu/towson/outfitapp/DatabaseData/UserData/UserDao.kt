package edu.towson.outfitapp.DatabaseData.UserData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User) // How to add a single user

    @Query("SELECT * FROM users ORDER BY userEmail ASC ")  // Gets all users
    fun readAllData(): LiveData<List<User>>


    @Delete
    suspend fun deleteUser(user: User) // How to delete a user

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers() // This deletes all user in the table (Please don't use unless you have too)

    @Query("SELECT * FROM users WHERE userName = :username")
    fun getUserByUsername(username: String): LiveData<User?> // Gets one particular user based on user name

    @Query("SELECT * FROM users WHERE userName = :username AND password = :password")
    fun login(username: String, password: String): LiveData<User?> // This will be used to do login verification




}